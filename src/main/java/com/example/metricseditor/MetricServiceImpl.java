package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service("metricservice")
public class MetricServiceImpl implements MetricService {

    @Autowired
    @Qualifier("metricrepository")
    private MetricRepository metricRepository;

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
    public Metric getMetricById(Long metricId) throws ResourceNotFoundException{
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        return metric;
    }

    @Override
    public Metric deleteMetric(Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        metricRepository.deleteById(metricId);
        return metric;
    }

    @Override
    public Metric updateMetric(Long metricId, @Valid @RequestBody Metric metricDetails) throws ResourceNotFoundException{
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));

        metric.setName(metricDetails.getName());
        metric.setPattern(metricDetails.getPattern());
        metric.setSubject(metricDetails.getSubject());
        metric.setType(metricDetails.getType());
        metric.setTeamextension(metricDetails.getTeamextension());
        metric.setObject(metricDetails.getObject());
        metric.setModifiers(metricDetails.getModifiers());
        metric.setConditions(metricDetails.getConditions());
        metric.setValue(metricDetails.getValue());
        metric.setCount(metricDetails.getCount());
        metric.setCount_attribute(metricDetails.getCount_attribute());

        return metricRepository.save(metric);
    }
}
