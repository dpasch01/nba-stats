package com.nba.stats.parser;

import com.nba.stats.endpoints.Endpoints;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.examples.Season2015_16;
import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class Parser {
    public static final String NBA_STATS_PATH = "./.nbastats/";
    public static final String PAGE_NOT_FOUND = "404. PAGE NOT FOUND.";
    public static final String ACCESS_DENIED = "Access Denied";
    public static BrowserEngine webkit = BrowserFactory.getWebKit();

    public static URI generateRequest(String endpoint, Parameters parameters) throws URISyntaxException, Endpoints.EndpointNotFound {
        if (Endpoints.ENDPOINTS.get(endpoint) == null) {
            throw new Endpoints.EndpointNotFound("Endpoint " + endpoint + " not found.");
        }
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("stats.nba.com").setPath("/stats/" + endpoint);
        for (Map.Entry<String, String> parameter : parameters.getValues().entrySet()) {
            builder.setParameter(parameter.getKey(), parameter.getValue());
        }

        URI uri = builder.build();
        Season2015_16.info("Generated URI: " + uri.toString());
        return uri;
    }

    public static Results parse(String endpoint, Parameters parameters) throws IOException, ParseException, URISyntaxException, Endpoints.EndpointNotFound, Parameters.ParameterNotFound {
        Page page = webkit.navigate(Parser.generateRequest(endpoint, parameters).toString());

        page.show();

        Document document = page.getDocument();
        String json = document.getBody().getText().get();

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

        page.close();
        return new Results((JSONObject) resultObject, parameters);
    }

    public static void main(String args[]) {
        Parameters parameters = new Parameters();
        try {
            parameters.addParameter("PlayerID", "101165");
            parameters.addParameter("PerMode", "Totals");
            Results results = Parser.parse("playerprofilev2", parameters);
            results.generateTSV("./");
            Parser.webkit.shutdown();
        } catch (Parameters.ParameterNotFound parameterNotFound) {
            parameterNotFound.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Endpoints.EndpointNotFound endpointNotFound) {
            endpointNotFound.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
