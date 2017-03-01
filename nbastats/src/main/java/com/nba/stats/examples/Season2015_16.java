package com.nba.stats.examples;

import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.parser.Parser;
import com.nba.stats.parser.Results;
import com.nba.stats.players.Players;
import com.nba.stats.teams.Teams;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Season2015_16 {
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

    public static class Collector implements Runnable {
        private Parameters parameters;
        private String endpoint;
        private String path;

        public Collector(Parameters parameters, String endpoint, String path) {
            this.parameters = parameters;
            this.endpoint = endpoint;
            this.path = path;
        }

        @Override
        public void run() {
            try {
                Results results = Parser.parse(this.getEndpoint(), this.getParameters());
                results.generateTSV(this.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (Endpoints.EndpointNotFound endpointNotFound) {
                endpointNotFound.printStackTrace();
            } catch (Parameters.ParameterNotFound parameterNotFound) {
                parameterNotFound.printStackTrace();
            }

        }

        public Parameters getParameters() {
            return parameters;
        }

        public void setParameters(Parameters parameters) {
            this.parameters = parameters;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public static void main(String args[]) {

        try {
            Parameters p = new Parameters();
            p.addParameter("LeagueID", "00");
            p.addParameter("Season", "2015-16");
            p.addParameter("isOnlyCurrentSeason", "1");

            List<String> players = null;
            try {
                players = Players.getPlayersOf(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            File endpoints = new File("./.nbastats");
            if (!endpoints.exists() || !endpoints.isDirectory()) {
                throw new NBAStatsEndpointsException("Base path not found.");
            }

            List<String> playerEndpoints = new ArrayList<>();
            File playerEndpointsFile = new File("players-regular-2015-16/.endpoints");
            if (!playerEndpointsFile.exists()) {
                playerEndpointsFile.getParentFile().mkdirs();
                playerEndpointsFile.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(playerEndpointsFile.getPath()));
            String pendpoint;
            while ((pendpoint = br.readLine()) != null) {
                playerEndpoints.add(pendpoint.toLowerCase());
            }
            br.close();

            List<String> teamEndpoints = new ArrayList<>();
            File teamEndpointsFile = new File("teams-regular-2015-16/.endpoints");
            if (!teamEndpointsFile.exists()) {
                teamEndpointsFile.getParentFile().mkdirs();
                teamEndpointsFile.createNewFile();
            }

            br = new BufferedReader(new FileReader(teamEndpointsFile.getPath()));
            String tendpoint;
            while ((tendpoint = br.readLine()) != null) {
                teamEndpoints.add(tendpoint.toLowerCase());
            }
            br.close();

            for (File endpointFile : endpoints.listFiles()) {

                Parameters parameters = new Parameters();
                String endpoint = endpointFile.getName().substring(0, endpointFile.getName().lastIndexOf("."));
                Season2015_16.info("Analyzing endpoint: " + endpoint);

                br = new BufferedReader(new FileReader(endpointFile.getPath()));
                String parameter;
                while ((parameter = br.readLine()) != null) {
                    if (!parameter.isEmpty()) {
                        parameters.addParameter(parameter, "");
                    }
                }
                br.close();

                if (parameters.getValues().containsKey("PlayerID")) {
                    if (playerEndpoints.contains(endpoint)) {
                        continue;
                    }
                    FileWriter playerEndpointWriter = new FileWriter("players-regular-2015-16/.endpoints", true);
                    BufferedWriter bp = new BufferedWriter(playerEndpointWriter);
                    for (String player : players) {
                        parameters.addParameter("PlayerID", player);
                        parameters.addParameter("LeagueID", "00");
                        parameters.addParameter("Season", "2015-16");
                        parameters.addParameter("PerMode", "PerGame");
                        parameters.addParameter("SeasonType", "Regular Season");
                        parameters.addParameter("AheadBehind", "Ahead or Behind");
                        parameters.addParameter("ClutchTime", "Last 5 Minutes");
                        parameters.addParameter("TeamID", "0");
                        parameters.addParameter("PlusMinus", "N");
                        parameters.addParameter("LastNGames", "0");
                        parameters.addParameter("MeasureType", "Base");
                        parameters.addParameter("isOnlyCurrentSeason", "1");
                        parameters.addParameter("Month", "0");
                        parameters.addParameter("OpponentTeamID", "0");
                        parameters.addParameter("PORound", "0");
                        parameters.addParameter("PointDiff", "5");
                        parameters.addParameter("Period", "0");
                        parameters.addParameter("Rank", "N");
                        parameters.addParameter("PaceAdjust", "N");

                        try {
                            Results results = Parser.parse(endpoint, parameters);
                            results.generateTSV("players-regular-2015-16/" + Players.PLAYERS.get(player).replaceAll(" ", "").replaceAll(",", "").toLowerCase());
                        } catch (ParseException e) {
                            continue;
                        } catch (NullPointerException e) {
                            continue;
                        }

                    }
                    bp.write(endpoint + "\n");
                    bp.close();
                } else if (parameters.getValues().containsKey("TeamID")) {
                    if (teamEndpoints.contains(endpoint)) {
                        continue;
                    }
                    FileWriter teamEndpointWriter = new FileWriter("teams-regular-2015-16/.endpoints", true);
                    BufferedWriter bt = new BufferedWriter(teamEndpointWriter);
                    for (String team : Teams.TEAMS.keySet()) {
                        parameters.addParameter("TeamID", team);
                        parameters.addParameter("LeagueID", "00");
                        parameters.addParameter("Season", "2015-16");
                        parameters.addParameter("PerMode", "PerGame");
                        parameters.addParameter("SeasonType", "Regular Season");
                        parameters.addParameter("AheadBehind", "Ahead or Behind");
                        parameters.addParameter("ClutchTime", "Last 5 Minutes");
                        parameters.addParameter("PlusMinus", "N");
                        parameters.addParameter("LastNGames", "0");
                        parameters.addParameter("MeasureType", "Base");
                        parameters.addParameter("isOnlyCurrentSeason", "1");
                        parameters.addParameter("Month", "0");
                        parameters.addParameter("OpponentTeamID", "0");
                        parameters.addParameter("PORound", "0");
                        parameters.addParameter("PointDiff", "5");
                        parameters.addParameter("Period", "0");
                        parameters.addParameter("Rank", "N");
                        parameters.addParameter("PaceAdjust", "N");
                        try {
                            Results results = Parser.parse(endpoint, parameters);
                            results.generateTSV("teams-regular-2015-16/" + Teams.TEAMS.get(team).replaceAll(" ", "-").toLowerCase());
                        } catch (ParseException e) {
                            continue;
                        } catch (NullPointerException e) {
                            continue;
                        }
                    }
                    bt.write(endpoint + "\n");
                    bt.close();
                }

                parameters.addParameter("LeagueID", "00");
                parameters.addParameter("Season", "2015-16");
                parameters.addParameter("PerMode", "PerGame");
                parameters.addParameter("SeasonType", "Regular Season");
                parameters.addParameter("AheadBehind", "Ahead or Behind");
                parameters.addParameter("ClutchTime", "Last 5 Minutes");
                parameters.addParameter("TeamID", "0");
                parameters.addParameter("PlusMinus", "N");
                parameters.addParameter("LastNGames", "0");
                parameters.addParameter("MeasureType", "Base");
                parameters.addParameter("isOnlyCurrentSeason", "1");
                parameters.addParameter("Month", "0");
                parameters.addParameter("OpponentTeamID", "0");
                parameters.addParameter("PORound", "0");
                parameters.addParameter("PointDiff", "5");
                parameters.addParameter("Period", "0");
                parameters.addParameter("Rank", "N");
                parameters.addParameter("PaceAdjust", "N");

                try {
                    Results results = Parser.parse(endpoint, parameters);
                    results.generateTSV("./");
                } catch (ParseException e) {
                    continue;
                } catch (NullPointerException e) {
                    continue;
                }
            }
        } catch (NBAStatsEndpointsException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Endpoints.EndpointNotFound endpointNotFound) {
            endpointNotFound.printStackTrace();
        } catch (Parameters.ParameterNotFound parameterNotFound) {
            parameterNotFound.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parser.webkit.shutdown();
    }

    public static class NBAStatsEndpointsException extends Exception {

        public NBAStatsEndpointsException() {
            super();
        }

        public NBAStatsEndpointsException(String message) {
            super(message);
        }

        public NBAStatsEndpointsException(String message, Throwable cause) {
            super(message, cause);
        }

        public NBAStatsEndpointsException(Throwable cause) {
            super(cause);
        }

    }

}
