package com.example.metricseditor.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conditions")
public class Condition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "type")
    private String type;

    @Column (name = "condition_attribute")
    private String condition_attribute;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;


    public Condition () {

    }

    public Condition(String type, String condition_attribute, Metric metric) {
        this.type = type;
        this.condition_attribute = condition_attribute;
        this.metric = metric;
    }

    public Condition(String type, String condition_attribute) {
        this.type = type;
        this.condition_attribute = condition_attribute;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition_attribute() {
        return condition_attribute;
    }

    public void setCondition_attribute(String condition_attribute) {
        this.condition_attribute = condition_attribute;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
