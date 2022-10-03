package com.example.metricseditor.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "modifiers")
public class Modifier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "type")
    private String type;

    @Column (name = "modifier_attribute")
    private String modifier_attribute;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;


    public Modifier () {

    }

    public Modifier(String type, String modifier_attribute, Metric metric) {
        this.type = type;
        this.modifier_attribute = modifier_attribute;
        this.metric = metric;
    }

    public Modifier(String type, String modifier_attribute) {
        this.type = type;
        this.modifier_attribute = modifier_attribute;
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

    public String getModifier_attribute() {
        return modifier_attribute;
    }

    public void setModifier_attribute(String modifier_attribute) {
        this.modifier_attribute = modifier_attribute;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}