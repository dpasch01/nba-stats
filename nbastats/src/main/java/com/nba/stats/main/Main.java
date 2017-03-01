package com.nba.stats.main;

import com.nba.stats.builder.Builder;
import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.examples.Season2015_16;
import com.nba.stats.parser.Results;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    final static Logger logger = Logger.getLogger(Season2015_16.class);

    private static Logger getLogger() {
        if (logger == null) {
            Logger.getLogger(Season2015_16.class);
        }
        return logger;
    }

    public static void error(String msg) {
        getLogger().error(msg);
    }

    public static void info(String msg) {
        getLogger().info(msg);
    }

    public static void main(String[] args) throws Endpoints.EndpointNotFound {
        try {
            Options options = new Options();
            options.addOption("endpoint", true, "The NBAStats endpoint you want to collect.");
            options.addOption("parameters", true, "Path to the .json file containing the parameters for the search.");
            options.addOption("output", true, "Path to the generated .tsv results file. Current directory as a default path.");
            options.addOption("show_endpoints", false, "Show all the possible endpoints.");
            options.addOption("show_parameters", false, "Show all the possible parameters and their values.");
            options.addOption("analyze_endpoint", true, "Analyze the requirements of the given endpoint.");
            options.addOption("help", false, "Print all the possible commands.");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            File endpoints = new File("./.nbastats");

            if (cmd.hasOption("help")) {
                Main.info("Listing all the possible commands.");
                String header = "\nCollect information from NBAStats based on their API endpoints.\n\n";
                String footer = "\n";

                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("NBAStats Collector", header, options, footer, true);
                return;
            }

            if (!endpoints.exists() || !endpoints.isDirectory()) {
                Main.error("NBAStats base path not found.");
                Main.info("Generating NBAStats base path and endpoints.");
                Builder.generateParameters();
                Builder.crossValidateParameters();
                Builder.viaWebkit();
                Builder.generateTeams();
                Builder.generatePlayers();
                Builder.generateSeasons();
                Builder.webkit.shutdown();
            }

            if (cmd.hasOption("show_endpoints")) {
                Main.info("Listing all the possible endpoints.");
                for (Map.Entry<String, URI> endpoint : Endpoints.ENDPOINTS.entrySet()) {
                    System.out.println(endpoint.getKey() + ":\t" + endpoint.getValue().toString());
                }
                return;
            }

            if (cmd.hasOption("show_parameters")) {
                Main.info("Listing all the possible parameters.");
                for (Map.Entry<String, String> parameter : Parameters.PARAMETERS.entrySet()) {
                    String parameterValue = parameter.getValue();
                    if (parameterValue.equals("Range")) {
                        System.out.println(parameter.getKey() + ":\t[ " + Parameters.RANGES.get(parameter.getKey()) + " ]");
                    } else {
                        System.out.println(parameter.getKey() + ":\t" + parameterValue);
                    }
                }
                return;
            }

            if (cmd.hasOption("analyze_endpoint")) {
                String endpoint = cmd.getOptionValue("analyze_endpoint");
                if (endpoint == null) {
                    Main.error("Endpoint value is required.");
                    return;
                }

                Main.info("Fetching details on endpoint: \"" + endpoint + "\".");
                List<String> parameters = new ArrayList<>();

                BufferedReader br = new BufferedReader(new FileReader(Builder.NBA_STATS_PATH + endpoint + ".txt"));
                String parameter;
                while ((parameter = br.readLine()) != null) {
                    parameters.add(parameter.toLowerCase());
                }
                br.close();

                System.out.println("Endpoint:\t" + endpoint);
                System.out.println("Resource URI:\t" + Endpoints.ENDPOINTS.get(endpoint));
                System.out.println("Endpoint's parameters:");
                for (String p : parameters) {
                    String parameterValue = Parameters.PARAMETERS.get(p);
                    if (parameterValue.equals("Range")) {
                        System.out.println(">\t" + p + ":\t[ " + Parameters.RANGES.get(p) + " ]");
                    } else {
                        System.out.println(">\t" + p + ":\t" + parameterValue);
                    }
                }

                return;
            }

            String endpoint = cmd.getOptionValue("endpoint");
            String parameterFile = cmd.getOptionValue("parameters");
            String outputPath = cmd.getOptionValue("output", "./");

            if (endpoint == null) {
                Main.error("Endpoint value is required.");
                return;
            }

            if (parameterFile == null) {
                Main.error("Parameter's file path is required.");
                return;
            }

            if (Endpoints.ENDPOINTS.get(endpoint) == null) {
                throw new Endpoints.EndpointNotFound("Endpoint " + endpoint + " not found.");
            }

            File pFile = new File(parameterFile);

            if (pFile.isFile() || pFile.exists()) {
                Main.info("Parsing parameters file into Parameters object.");
                JSONParser parameterParser = new JSONParser();
                JSONObject parameterJSON = (JSONObject) parameterParser.parse(new FileReader(pFile));
                Parameters parameters = new Parameters();

                for (Object key : parameterJSON.keySet()) {
                    parameters.addParameter((String) key, (String) parameterJSON.get(key));
                }

                Main.info("Fetching NBAStats query data from " + endpoint + ".");
                Results results = com.nba.stats.parser.Parser.parse(endpoint, parameters);
                Main.info("Generating .tsv file with results from " + endpoint + ".");
                results.generateTSV(outputPath);
            } else {
                Main.error("Parameter file is not valid.");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } catch (Parameters.ParameterNotFound parameterNotFound) {
            parameterNotFound.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
