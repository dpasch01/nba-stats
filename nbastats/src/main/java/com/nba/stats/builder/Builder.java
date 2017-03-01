package com.nba.stats.builder;

import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.examples.Season2015_16;
import com.sun.deploy.net.HttpResponse;
import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Element;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Builder {
    public static final String NBA_STATS_PATH = "./.nbastats/";
    public static final String PAGE_NOT_FOUND = "404. PAGE NOT FOUND.";
    public static final String ACCESS_DENIED = "Access Denied";
    public static final List<String> ignoreWords = new ArrayList<>();

    static {
        Builder.ignoreWords.add("property");
        Builder.ignoreWords.add(".");
        Builder.ignoreWords.add("is");
        Builder.ignoreWords.add("the");
        Builder.ignoreWords.add("pass");
        Builder.ignoreWords.add("required.");
        Builder.ignoreWords.add("required,");
        Builder.ignoreWords.add("required");
        Builder.ignoreWords.add("default");
        Builder.ignoreWords.add("0");
        Builder.ignoreWords.add("");
        Builder.ignoreWords.add("for");
    }

    public static BrowserEngine webkit = BrowserFactory.getWebKit();

    public static void viaWebkit() throws FileNotFoundException, UnsupportedEncodingException {
        File nbastats = new File(Builder.NBA_STATS_PATH);
        if (!nbastats.exists()) {
            Season2015_16.info("Generating NBA Stats' base directory.");
            nbastats.mkdir();
        }

        for (String endpoint : Endpoints.ENDPOINTS.keySet()) {
            Season2015_16.info("Trying to build endpoint: \"" + endpoint + "\".");
            Season2015_16.info("Requesting " + Endpoints.ENDPOINTS.get(endpoint) + ".");

            Page page = webkit.navigate(Endpoints.ENDPOINTS.get(endpoint).toString());
            page.show();

            Document document = page.getDocument();
            String response = document.getBody().getText().get();
            if (response.contains(Builder.ACCESS_DENIED)) {
                Season2015_16.error(Builder.ACCESS_DENIED);
                continue;
            }
            if (response.contains(Builder.PAGE_NOT_FOUND)) {
                Season2015_16.error(Builder.PAGE_NOT_FOUND);
                continue;
            }
            Season2015_16.info(response);

            List<String> parameters = parseRequirements(response);
            generateEndpoint(endpoint, parameters);

        }
    }

    public static void generateParameters() throws FileNotFoundException, UnsupportedEncodingException {
        HashSet<String> parameters = new HashSet<>();
        HashMap<String, List<String>> endpoints = new HashMap<>();

        for (String endpoint : Endpoints.ENDPOINTS.keySet()) {
            Season2015_16.info("Trying to build endpoint: \"" + endpoint + "\".");
            Season2015_16.info("Requesting " + Endpoints.ENDPOINTS.get(endpoint) + ".");

            Page page = webkit.navigate(Endpoints.ENDPOINTS.get(endpoint).toString());
            page.show();

            Document document = page.getDocument();
            String response = document.getBody().getText().get();
            if (response.contains(Builder.ACCESS_DENIED)) {
                Season2015_16.error(Builder.ACCESS_DENIED);
                continue;
            }
            if (response.contains(Builder.PAGE_NOT_FOUND)) {
                Season2015_16.error(Builder.PAGE_NOT_FOUND);
                continue;
            }
            Season2015_16.info(response);

            response = response.toLowerCase();

            for (String requirement : response.split(";")) {
                String parameter = "";

                for (String p : requirement.split(" ")) {
                    if (Builder.ignoreWords.contains(p)) {
                        continue;
                    }
                    parameter += p;
                }

                parameters.add(parameter);
                if (endpoints.containsKey(parameter)) {
                    endpoints.get(parameter).add(endpoint);
                } else {
                    endpoints.put(parameter, new ArrayList<>());
                    endpoints.get(parameter).add(endpoint);
                }
            }
        }

        PrintWriter parametersWriter = new PrintWriter("parameters.txt", "UTF-8");
        List<String> sorted = new ArrayList<>(parameters);
        Collections.sort(sorted);

        for (String parameter : sorted) {
            Season2015_16.info(parameter + ": " + endpoints.get(parameter));
            parametersWriter.println(parameter);
        }

        parametersWriter.close();
    }

    public static void crossValidateParameters() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("parameters.txt"));

        String parameter = "";
        while ((parameter = br.readLine()) != null) {
            if (Parameters.PARAMETERS.get(parameter) == null) {
                Season2015_16.error(parameter + " not found.");
            }
        }

        br.close();
    }

    public static void generateTeams() throws FileNotFoundException, UnsupportedEncodingException {
        String teamEndpoint = "http://stats.nba.com/teams/traditional/#!?sort=W_PCT&dir=-1";
        Season2015_16.info("Trying to build endpoint: \"" + teamEndpoint + "\".");
        Season2015_16.info("Requesting " + teamEndpoint + ".");

        PrintWriter writer = new PrintWriter("teams.txt", "UTF-8");

        Page page = webkit.navigate(teamEndpoint);
        page.show();

        Document document = page.getDocument();
        for (Element e : document.getBody().queryAll("td.first a")) {
            String response = e.getAttribute("href").get().replaceAll("/team/#!/", "").replaceAll("/traditional/", "");
            response += "=" + e.getText().get();
            Season2015_16.info(response);
            writer.println(response);
        }

        writer.close();
        page.close();
    }

    public static void generateSeasons() throws FileNotFoundException, UnsupportedEncodingException {
        String seasonEndpoint = "http://www.nba.com/history/nba-season-recaps/";
        Season2015_16.info("Trying to build endpoint: \"" + seasonEndpoint + "\".");
        Season2015_16.info("Requesting " + seasonEndpoint + ".");

        PrintWriter writer = new PrintWriter("seasons.txt", "UTF-8");

        Page page = webkit.navigate(seasonEndpoint);
        page.show();

        Document document = page.getDocument();
        for (Element e : document.getBody().queryAll("tr.cnnIERowAltBG td a")) {
            String response = e.getText().get();
            Season2015_16.info(response);
            writer.println(response);
        }

        writer.close();
        page.close();
    }

    private static void generateEndpoint(String endpoint, List<String> parameters) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(Builder.NBA_STATS_PATH + endpoint + ".txt", "UTF-8");
        for (String parameter : parameters) {
            writer.println(parameter);
        }
        writer.close();
    }

    private static List<String> parseRequirements(String reqs) {
        List<String> parameters = new ArrayList<>();

        for (String req : reqs.split(";")) {
            for (String parameter : req.split(" ")) {
                String type = Parameters.PARAMETERS.get(parameter);
                if (type != null) {
                    parameters.add(parameter);
                }
            }
        }

        return parameters;
    }

    public static HttpResponse http(String url, String body) throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(body);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        String json = EntityUtils.toString(httpClient.execute(request).getEntity(), "UTF-8");
        JSONParser parser = new JSONParser();
        Object resultObject = parser.parse(json);

        if (resultObject instanceof JSONArray) {
            JSONArray array = (JSONArray) resultObject;
            for (Object object : array) {
                JSONObject obj = (JSONObject) object;
                System.out.println(obj.toString());
            }

        } else if (resultObject instanceof JSONObject) {
            JSONObject obj = (JSONObject) resultObject;
            System.out.println(obj.toString());
        }

        return null;
    }

    public static void generatePlayers() throws FileNotFoundException, UnsupportedEncodingException {
        String playersEndpoint = "http://stats.nba.com/players/list/#!?Historic=Y";
        Season2015_16.info("Trying to build endpoint: \"" + playersEndpoint + "\".");
        Season2015_16.info("Requesting " + playersEndpoint + ".");

        PrintWriter writer = new PrintWriter("players.txt", "UTF-8");

        Page page = webkit.navigate(playersEndpoint);
        page.show();

        Document document = page.getDocument();
        for (Element e : document.getBody().queryAll("a.players-list__name")) {
            String response = e.getAttribute("href").get().replaceAll("/player/#!/", "").replaceAll("/", "");
            response += "=" + e.getText().get();
            Season2015_16.info(response);
            writer.println(response);
        }

        writer.close();
        page.close();
    }

    public static void main(String[] args) {
        try {
            Builder.generateParameters();
            Builder.crossValidateParameters();
            Builder.viaWebkit();
            Builder.generateTeams();
            Builder.generatePlayers();
            Builder.generateSeasons();
            Builder.webkit.shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
