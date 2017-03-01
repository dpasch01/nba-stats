package com.nba.stats.teams;

import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.parser.Parser;
import com.nba.stats.players.Players;
import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teams {
    public static HashMap<String, String> TEAMS = new HashMap<>();
    public static BrowserEngine webkit = BrowserFactory.getWebKit();

    public static String getTeamID(String name) throws Players.PlayerNotFound, TeamNotFound {
        String team = Teams.TEAMS.get(name);
        if (name == null) {
            throw new TeamNotFound("Team with name: " + name + " not found.");
        }

        return team;
    }

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("teams.txt"));
            String team = "";
            while ((team = br.readLine()) != null) {
                String[] teamItem = team.split("=");
                Teams.TEAMS.put(teamItem[0], teamItem[1]);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTeamsOf(Parameters parameters) throws URISyntaxException, Endpoints.EndpointNotFound, ParseException {
        Page page = webkit.navigate(Parser.generateRequest("commonallplayers", parameters).toString());
        page.show();

        Document document = page.getDocument();
        String json = document.getBody().getText().get();

        List<String> players = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object resultObject = parser.parse(json);

        JSONObject obj = (JSONObject) resultObject;
        JSONObject results = (JSONObject) ((JSONArray) obj.get("resultSets")).get(0);
        JSONArray rows = (JSONArray) results.get("rowSet");
        rows.forEach(o -> {
            JSONArray set = (JSONArray) o;
            players.add(set.get(0).toString());
        });

        return players;
    }

    public static class TeamNotFound extends Exception {

        public TeamNotFound() {
            super();
        }

        public TeamNotFound(String message) {
            super(message);
        }

        public TeamNotFound(String message, Throwable cause) {
            super(message, cause);
        }

        public TeamNotFound(Throwable cause) {
            super(cause);
        }

    }
}
