package com.example.metricseditor.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "metrics")
public class Metric implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "metric_name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "pattern")
    private String pattern;

    @Column (name = "subject")
    private String subject;

    @Column (name = "type")
    private String type;

    @Column (name = "team_extension")
    private String teamextension;

    @Column (name = "object")
    private String object;

    @OneToMany(mappedBy = "metric", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Modifier> modifiers;

    @OneToMany(mappedBy = "metric", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Condition> conditions;

    @Column (name = "value")
    private String value;

    @Column (name = "count")
    private String count;

    @Column (name = "count_attribute")
    private String count_attribute;


    public Metric() {
    }

    public Metric(String name, String description, String pattern, String subject, String type, String teamextension, String object, String value, String count, String count_attribute) {
        this.name = name;
        this.description = description;
        this.pattern = pattern;
        this.subject = subject;
        this.type = type;
        this.teamextension = teamextension;
        this.object = object;
        this.value = value;
        this.count = count;
        this.count_attribute = count_attribute;
    }

    public Metric(Long id, String name, String description, String pattern, String subject, String type, String teamextension, String object,
                  List<Modifier> modifiers, List<Condition> conditions, String value,
                  String count, String count_attribute) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pattern = pattern;
        this.subject = subject;
        this.type = type;
        this.teamextension = teamextension;
        this.object = object;
        this.modifiers = modifiers;
        this.conditions = conditions;
        this.value = value;
        this.count = count;
        this.count_attribute = count_attribute;
    }

    public Metric(String name, String description, String pattern, String subject, String type, String teamextension, String object,
                  List<Modifier> modifiers, List<Condition> conditions, String value, String count, String count_attribute) {
        this.name = name;
        this.description = description;
        this.pattern = pattern;
        this.subject = subject;
        this.type = type;
        this.teamextension = teamextension;
        this.object = object;
        this.modifiers = modifiers;
        this.conditions = conditions;
        this.value = value;
        this.count = count;
        this.count_attribute = count_attribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeamextension() {
        return teamextension;
    }

    public void setTeamextension(String teamextension) {
        this.teamextension = teamextension;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Modifier> getModifiers() { return modifiers;}

    public void setModifiers(List<Modifier> modifiers) { this.modifiers = modifiers; }

    public List<Condition> getConditions() { return conditions;}

    public void setConditions(List<Condition> conditions) { this.conditions = conditions; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount_attribute() {
        return count_attribute;
    }

    public void setCount_attribute(String count_attribute) {
        this.count_attribute = count_attribute;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pattern='" + pattern + '\'' +
                ", subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                ", teamextension='" + teamextension + '\'' +
                ", object='" + object + '\'' +
                ", modifier='" + modifiers + '\'' +
                ", condition='" + conditions + '\'' +
                ", value='" + value + '\'' +
                ", count='" + count + '\'' +
                ", count_attribute='" + count_attribute + '\'' +
                '}';
    }
}

