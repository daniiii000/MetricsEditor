package com.example.metricseditor;

import com.example.metricseditor.files.FileOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
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
    public Metric addMetric(@RequestBody Metric metric) {
        metric.setName(metric.getName());
        metric.setPattern(metric.getPattern());
        metric.setSubject(metric.getSubject());
        metric.setType(metric.getType());
        metric.setTeamextension(metric.getTeamextension());
        metric.setObject(metric.getObject());
        metric.setModifiers(metric.getModifiers());
        metric.setConditions(metric.getConditions());
        metric.setValue(metric.getValue());
        metric.setCount(metric.getCount());
        metric.setCount_attribute(metric.getCount_attribute());
        return metricRepository.save(metric);
    }

    @Override
    public Metric getMetricById(Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        return metric;
    }

    @Override
    public Metric deleteMetric(Long metricId) throws ResourceNotFoundException {
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
    public Metric updateMetric(Long metricId, HttpServletRequest request) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));

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

        metric.setName(name);
        metric.setPattern(pattern);
        metric.setSubject(subject);
        metric.setType(type);
        metric.setTeamextension(teamextension);
        metric.setObject(object);


       /* List<Condition> conditions = conditionRepository.findAllByMetric(metricId);
        List<Modifier> modifiers_list = new ArrayList<>();
        List<Condition> conditions_list = new ArrayList<>();
        Condition condition;
        Modifier modifier;

        if (conditions.size() == 0) {
            condition = new Condition(condition_type, condition_attribute);
            condition.setMetric(metric);
            conditionRepository.save(condition);
        }

        List<Modifier> modifiers = modifierRepository.findAllByMetric(metricId);

        if (modifiers.size() == 0) {
            modifier = new Modifier(modifier_type, modifier_attribute);
            modifier.setMetric(metric);
            modifierRepository.save(modifier);
        }

        condition.setMetric(metric);
        conditionRepository.save(condition);

        modifier.setMetric(metric);
        modifierRepository.save(modifier);

        conditions_list.add(condition);


        modifiers_list.add(modifier);
        metric.setConditions(conditions_list);
        metric.setModifiers(modifiers_list);*/

        metricRepository.save(metric);
        return metric;
    }
}
