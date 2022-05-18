package com.example.metricseditor;


public class Query {

    private String queryname;
    private String dashboard;
    private String metric;
    private String function;

    public String getQueryname() {return queryname;}
    public void getQueryname(String queryname) {this.queryname = queryname;}

    public String getDashboard() {return dashboard;}
    public void setDashboard(String dashboard) {this.dashboard = dashboard;}

    public String getMetric() {return metric;}
    public void setMetric(String metric) {this.metric = metric;}

    public String getFunction() {return function;}
    public void setFunction(String function) {this.function = function;}
}

