package com.example.metricseditor.serviceImpl;

import com.example.metricseditor.exceptions.ResourceNotFoundException;
import com.example.metricseditor.files.FileOperations;
import com.example.metricseditor.models.Condition;
import com.example.metricseditor.models.Metric;
import com.example.metricseditor.models.Modifier;
import com.example.metricseditor.repositories.ConditionRepository;
import com.example.metricseditor.repositories.MetricRepository;
import com.example.metricseditor.repositories.ModifierRepository;
import com.example.metricseditor.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("metricservice")
public class MetricServiceImpl implements MetricService {

    @Autowired
    @Qualifier("metricrepository")
    private MetricRepository metricRepository;

    @Autowired
    @Qualifier("conditionrepository")
    private ConditionRepository conditionRepository;

    @Autowired
    @Qualifier("modifierrepository")
    private ModifierRepository modifierRepository;

    @Override
    public List<Metric> getAllMetrics() {
        return (List<Metric>) metricRepository.findAll();
    }

    @Override
    public void addMetric(HttpServletRequest request) throws IOException {

        String pattern = request.getParameter("pattern");
        String type = request.getParameter("type");
        String subject = request.getParameter("subject");
        String teamextension = request.getParameter("teamextension");
        String object = request.getParameter("object");
        String modifier_type = request.getParameter("modifier");
        String modifier_attribute = request.getParameter("modifier_attribute");
        String condition_type = request.getParameter("condition");
        String condition_attribute = request.getParameter("condition_attribute");
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String count_type = request.getParameter("count");
        String count_attribute = request.getParameter("count_attribute");
        String description = request.getParameter("description");

        List<Condition> conditions_list = new ArrayList<>();
        List<Modifier> modifiers_list = new ArrayList<>();

        if (pattern.equals("Percentage")) {
            Metric metric = new Metric(name, description, pattern,subject,type,teamextension,object,null,null,null);
            metricRepository.save(metric);

            Condition condition = new Condition(condition_type, condition_attribute);
            condition.setMetric(metric);
            conditionRepository.save(condition);
            Modifier modifier = new Modifier(modifier_type,modifier_attribute);
            modifier.setMetric(metric);
            modifierRepository.save(modifier);
            conditions_list.add(condition);

            modifiers_list.add(modifier);
            metric.setConditions(conditions_list);
            metric.setModifiers(modifiers_list);

            metricRepository.save(metric);
        }
        else if (pattern.equals("Standard Deviation")) {
            Metric metric = new Metric(name, description, pattern,subject,type,teamextension,object,value,null,null);
            metricRepository.save(metric);
            Modifier modifier = new Modifier(modifier_type,modifier_attribute);
            modifier.setMetric(metric);
            modifierRepository.save(modifier);

            modifiers_list.add(modifier);
            metric.setModifiers(modifiers_list);

            metricRepository.save(metric);
        }
        else if (pattern.equals("Frequency")) {
            Metric metric = new Metric(name, description, pattern,subject,type,teamextension,object,null,count_type,count_attribute);
            metricRepository.save(metric);
        }

        FileOperations.createProperties(name, description, object, pattern, subject, teamextension, conditions_list, modifiers_list);
        FileOperations.createQueries(name, object, pattern, modifiers_list, conditions_list, subject, teamextension, count_type, count_attribute);
    }

    @Override
    public Metric getMetricById(Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        return metric;
    }

    @Override
    public Metric deleteMetric(Long metricId) throws ResourceNotFoundException, IOException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        FileOperations.deleteProperties(metric.getName());
        FileOperations.deleteQueries(metric.getName());
        metricRepository.deleteById(metricId);
        return metric;
    }

    @Override
    public String getPropertiesFile(Long metricId) throws Exception {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        String text = FileOperations.readProperties(metric.getName());
        return text;
    }

    @Override
    public String getQueryFile(Long metricId) throws Exception {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        String text = FileOperations.readQueries(metric.getName());
        return text;
    }

    @Override
    public Metric updateMetric(Long metricId, HttpServletRequest request, String name_before) throws ResourceNotFoundException, IOException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));

        FileOperations.deleteProperties(name_before);
        FileOperations.deleteQueries(name_before);

        String pattern = request.getParameter("pattern");
        String type = request.getParameter("type");
        String subject = request.getParameter("subject");
        String teamextension = request.getParameter("teamextension");
        String object = request.getParameter("object");
        String modifier_type = request.getParameter("modifier");
        String modifier_attribute = request.getParameter("modifier_attribute");
        String condition_type = request.getParameter("condition");
        String condition_attribute = request.getParameter("condition_attribute");
        String value = request.getParameter("value");
        String count_type = request.getParameter("count");
        String count_attribute = request.getParameter("count_attribute");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        List<Condition> conditions_list = new ArrayList<>();
        List<Modifier> modifiers_list = new ArrayList<>();

        metric.setName(name);
        metric.setDescription(description);
        metric.setPattern(pattern);
        metric.setSubject(subject);
        metric.setType(type);
        metric.setTeamextension(teamextension);
        metric.setObject(object);

        if (pattern.equals("Percentage")) {
            Condition condition = conditionRepository.findByMetricId(metricId);
            if (condition.getType() == null && condition.getCondition_attribute() == null) {
                condition = new Condition(condition_type, condition_attribute);
            }
            else {
                condition.setType(condition_type);
                condition.setCondition_attribute(condition_attribute);
            }
            condition.setMetric(metric);
            conditionRepository.save(condition);

            Modifier modifier = modifierRepository.findByMetricId(metricId);
            if (modifier.getModifier_attribute() == null && modifier.getType() == null) {
                modifier = new Modifier(modifier_type,modifier_attribute);
            }
            else {
                modifier.setType(modifier_type);
                modifier.setModifier_attribute(modifier_attribute);
            }
            modifier.setMetric(metric);
            modifierRepository.save(modifier);

            conditions_list.add(condition);
            modifiers_list.add(modifier);
            metric.setConditions(conditions_list);
            metric.setModifiers(modifiers_list);

            metricRepository.save(metric);
        }
        else if (pattern.equals("Standard Deviation")) {
            Modifier modifier = modifierRepository.findByMetricId(metricId);
            if (modifier.getModifier_attribute() == null && modifier.getType() == null) {
                modifier = new Modifier(modifier_type,modifier_attribute);
            }
            else {
                modifier.setType(modifier_type);
                modifier.setModifier_attribute(modifier_attribute);
            }
            modifier.setMetric(metric);
            modifierRepository.save(modifier);

            modifiers_list.add(modifier);
            metric.setModifiers(modifiers_list);

            metricRepository.save(metric);
        }
        else if (pattern.equals("Frequency")) {
            metric.setCount(count_type);
            metric.setCount_attribute(count_attribute);
            metricRepository.save(metric);
        }

        FileOperations.createProperties(name, description, object, pattern, subject, teamextension, conditions_list, modifiers_list);
        FileOperations.createQueries(name, object, pattern, modifiers_list, conditions_list, subject, teamextension, count_type, count_attribute);

        return metric;
    }
}
