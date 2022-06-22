package com.example.metricseditor;

import javax.persistence.*;

@Entity
@Table(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "metric_name")
    private String name;

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

    @Column (name = "modifier")
    private String modifier;

    @Column (name = "modifier_attribute")
    private String modifier_attribute;

    @Column (name = "condition")
    private String condition;

    @Column (name = "condition_attribute")
    private String condition_attribute;

    @Column (name = "value")
    private String value;

    @Column (name = "value_attribute")
    private String value_attribute;

    @Column (name = "count")
    private String count;

    @Column (name = "count_attribute")
    private String count_attribute;


    public Metric() {

    }

    public Metric(Long id, String name, String pattern, String subject, String type, String teamextension, String object,
                  String modifier, String modifier_attribute, String condition,String condition_attribute, String value,
                  String value_attribute, String count, String count_attribute) {
        this.id = id;
        this.name = name;
        this.pattern = pattern;
        this.subject = subject;
        this.type = type;
        this.teamextension = teamextension;
        this.object = object;
        this.modifier = modifier;
        this.modifier_attribute = modifier_attribute;
        this.condition = condition;
        this.condition_attribute = condition_attribute;
        this.value = value;
        this.value_attribute = value_attribute;
        this.count = count;
        this.count_attribute = count_attribute;
    }

    public Metric(String name, String pattern, String subject, String type, String teamextension, String object,
                  String modifier, String modifier_attribute, String condition,String condition_attribute, String value,
                  String value_attribute, String count, String count_attribute) {
        this.name = name;
        this.pattern = pattern;
        this.subject = subject;
        this.type = type;
        this.teamextension = teamextension;
        this.object = object;
        this.modifier = modifier;
        this.modifier_attribute = modifier_attribute;
        this.condition = condition;
        this.condition_attribute = condition_attribute;
        this.value = value;
        this.value_attribute = value_attribute;
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier_attribute() {
        return modifier_attribute;
    }

    public void setModifier_attribute(String modifier_attribute) {
        this.modifier_attribute = modifier_attribute;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition_attribute() {
        return condition_attribute;
    }

    public void setCondition_attribute(String condition_attribute) {
        this.condition_attribute = condition_attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue_attribute() {
        return value_attribute;
    }

    public void setValue_attribute(String value_attribute) {
        this.value_attribute = value_attribute;
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
    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pattern='" + pattern + '\'' +
                ", subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                ", teamextension='" + teamextension + '\'' +
                ", object='" + object + '\'' +
                ", modifier='" + modifier + '\'' +
                ", modifier_attribute='" + modifier_attribute + '\'' +
                ", condition='" + condition + '\'' +
                ", condition_attribute='" + condition_attribute + '\'' +
                ", value='" + value + '\'' +
                ", value_attribute='" + value_attribute + '\'' +
                ", count='" + count + '\'' +
                ", count_attribute='" + count_attribute + '\'' +
                '}';
    }
}

