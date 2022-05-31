package com.example.metricseditor;

public class Query {
    private Long id;
    private String index;
    private String metric;
    private String function;

    public Query() {

    }

    public Query(Long id, String index, String metric, String function) {
        this.id = id;
        this.index = index;
        this.metric = metric;
        this.function = function;
    }

    public Query(String index, String metric, String function) {
        this.index = index;
        this.metric = metric;
        this.function = function;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getIndex() {return index;}
    public void setIndex(String index) {this.index = index;}

    public String getMetric() {return metric;}
    public void setMetric(String metric) {this.metric = metric;}

    public String getFunction() {return function;}
    public void setFunction(String function) {this.function = function;}

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", index='" + index + '\'' +
                ", metric='" + metric + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}

