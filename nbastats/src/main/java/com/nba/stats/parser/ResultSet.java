package com.nba.stats.parser;

import java.util.List;

public class ResultSet {
    private String title;
    private String[] headers;
    private List<String[]> rows;

    public ResultSet(String title, String[] headers, List<String[]> rows) {
        this.title = title;
        this.headers = headers;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }
}
