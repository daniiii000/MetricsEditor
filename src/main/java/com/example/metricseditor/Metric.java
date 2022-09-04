package com.example.metricseditor;

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
    private Set<Modifier> modifiers;

    @OneToMany(mappedBy = "metric", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Condition> conditions;

    @Column (name = "value")
    private String value;

    @Column (name = "count")
    private String count;

    @Column (name = "count_attribute")
    private String count_attribute;

   /* public void addModifier(Modifier modifier) {
        if (null == modifier) {
            modifiers = new HashSet<>();
        }
        modifiers.add(modifier);
        modifiers.setModifiers(this);
    }
    public void removeModifier(Modifier modifier) {
        modifiers.remove(modifier);
        modifiers.setModifiers(null);
    }

    public void addCondition(Condition condition) {
        if (null == condition) {
            conditions = new HashSet<>();
        }
        conditions.add(condition);
        conditions.setConditions(this);
    }
    public void removeCondition(Condition condition) {
        conditions.remove(condition);
        conditions.setConditions(null);
    }*/

    public Metric() {
    }

    public Metric(Long id, String name, String pattern, String subject, String type, String teamextension, String object,
                  Set<com.example.metricseditor.Modifier> modifiers, Set<Condition> conditions, String value,
                  String count, String count_attribute) {
        this.id = id;
        this.name = name;
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

    public Metric(String name, String pattern, String subject, String type, String teamextension, String object,
                  Set<com.example.metricseditor.Modifier> modifiers, Set<Condition> conditions, String value, String count, String count_attribute) {
        this.name = name;
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

    public Set<Modifier> getModifiers() { return modifiers;}

    public void setModifiers(Set<Modifier> modifiers) { this.modifiers = modifiers; }

    public Set<Condition> getConditions() { return conditions;}

    public void setConditions(Set<Condition> conditions) { this.conditions = conditions; }

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
                ", modifier='" + modifiers + '\'' +
                ", condition='" + conditions + '\'' +
                ", value='" + value + '\'' +
                ", count='" + count + '\'' +
                ", count_attribute='" + count_attribute + '\'' +
                '}';
    }
}

