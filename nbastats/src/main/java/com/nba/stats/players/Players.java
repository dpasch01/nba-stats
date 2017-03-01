package com.nba.stats.players;

import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.parser.Parser;
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

public class Players {
    public static HashMap<String, String> PLAYERS = new HashMap<>();
    public static BrowserEngine webkit = BrowserFactory.getWebKit();

    public static String getPlayerID(String name) throws PlayerNotFound {
        String player = Players.PLAYERS.get(name);
        if (name == null) {
            throw new PlayerNotFound("Player with name: " + name + " not found.");
        }

        return player;
    }

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("players.txt"));
            String player = "";
            while ((player = br.readLine()) != null) {
                String[] playerItem = player.split("=");
                Players.PLAYERS.put(playerItem[0], playerItem[1]);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPlayersOf(Parameters parameters) throws URISyntaxException, Endpoints.EndpointNotFound, ParseException {
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

    public static class PlayerNotFound extends Exception {

        public PlayerNotFound() {
            super();
        }

        public PlayerNotFound(String message) {
            super(message);
        }

        public PlayerNotFound(String message, Throwable cause) {
            super(message, cause);
        }

        public PlayerNotFound(Throwable cause) {
            super(cause);
        }

    }

}
