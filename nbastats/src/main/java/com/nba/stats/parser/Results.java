package com.nba.stats.parser;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.nba.stats.database.Database;
import com.nba.stats.endpoints.Parameters;
import com.nba.stats.examples.Season2015_16;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Results {
    private String endpoint;
    private Parameters parameters;
    private List<ResultSet> results;
    private JSONObject json;

    public Results(JSONObject json, Parameters parameters) throws Parameters.ParameterNotFound {
        this.json = json;
        this.endpoint = json.get("resource").toString();
        this.parameters = parameters;

        /*
        JSONObject params = (JSONObject) json.get("parameters");
        System.out.println(params);
        for (Object field : params.keySet()) {
            String value = params.get(field.toString()) + "";
            if (value.equals("null") || value.equals("") || value.isEmpty()) {
                value = "null";
            }
            this.parameters.addParameter(field.toString(), value);
        }
        */

        this.results = new ArrayList<>();
        JSONArray resultSets = (JSONArray) json.get("resultSets");
        resultSets.forEach(o -> {

            String title = ((JSONObject) o).get("name") + "";
            JSONArray headers = (JSONArray) ((JSONObject) o).get("headers");
            JSONArray rows = (JSONArray) ((JSONObject) o).get("rowSet");

            List<String> h = new ArrayList<>();
            headers.forEach(o1 -> {
                String value = o1 + "";
                if (value.equals("null") || value.equals("") || value.isEmpty()) {
                    value = "null";
                }
                h.add(value);
            });

            List<String[]> rs = new ArrayList<>();
            rows.forEach(o2 -> {
                List r = new ArrayList();
                ((JSONArray) o2).forEach(o3 -> {
                    String value = o3 + "";
                    if (value.equals("null") || value.equals("") || value.isEmpty()) {
                        value = "null";
                    }
                    r.add(value);
                });
                rs.add((String[]) r.toArray(new String[rs.size()]));
            });

            this.results.add(new ResultSet(title, h.toArray(new String[h.size()]), rs));
        });
    }

    @Override
    public String toString() {
        return this.json.toString();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public List<ResultSet> getResults() {
        return results;
    }

    public void generateTSV(String path) throws FileNotFoundException, UnsupportedEncodingException {
        if (path.lastIndexOf("/") != path.length()) {
            path += "/";
        }

        File outputFolder = new File(path + "" + this.endpoint + System.currentTimeMillis());
        if (!outputFolder.exists()) {
            Season2015_16.info("Generating output directory for " + this.endpoint + ".");
            outputFolder.getParentFile().mkdirs();
            outputFolder.mkdir();
        }

        Season2015_16.info("Writing query file for " + this.endpoint + ".");
        PrintWriter parametersWriter = new PrintWriter(outputFolder.getPath() + "/" + this.endpoint + ".qry", "UTF-8");
        for (Map.Entry<String, String> parameter : this.getParameters().getValues().entrySet()) {
            parametersWriter.println(parameter.getKey() + "=" + parameter.getValue());
        }
        parametersWriter.close();

        for (ResultSet set : this.getResults()) {

            Season2015_16.info("Writing result set for \"" + outputFolder.getPath() + "/" + set.getTitle() + "\".");
            PrintWriter resultSetWriter = new PrintWriter(outputFolder.getPath() + "/" + set.getTitle() + ".tsv", "UTF-8");

            String[] headers = set.getHeaders();
            for (int i = 0; i < headers.length - 1; i++) {
                resultSetWriter.print(headers[i] + "\t");
            }
            resultSetWriter.println(headers[headers.length - 1]);

            for (String[] row : set.getRows()) {
                for (int i = 0; i < row.length - 1; i++) {
                    resultSetWriter.print(row[i] + "\t");
                }
                resultSetWriter.println(row[row.length - 1]);
            }

            resultSetWriter.close();
        }

    }

    public void store2Database(Database database) {
        Mongo mongo = new Mongo(database.getHost(), database.getPort());
        DB db = mongo.getDB(database.getDatabase());
        DBCollection collection = db.getCollection(this.endpoint);
        DBObject dbObject = (DBObject) JSON.parse(this.json.toJSONString());
        collection.insert(dbObject);
    }

    public void generateJSON(String path) throws FileNotFoundException, UnsupportedEncodingException {
        if (path.lastIndexOf("/") != path.length()) {
            path += "/";
        }
        PrintWriter resultSetWriter = new PrintWriter(path + "" + this.endpoint + System.currentTimeMillis(), "UTF-8");
        resultSetWriter.print(this.json.toString());
        resultSetWriter.close();
    }

    public void setResults(List<ResultSet> results) {
        this.results = results;
    }
}
