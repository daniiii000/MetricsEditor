package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        return metricRepository.findAll();
    }

    @Override
    public Metric addMetric(@RequestBody Metric metric) {
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
        metric.setModifier(metricDetails.getModifier());
        metric.setModifier_attribute(metricDetails.getModifier_attribute());
        metric.setCondition(metricDetails.getCondition());
        metric.setCondition_attribute(metricDetails.getCondition_attribute());
        metric.setValue(metricDetails.getValue());
        metric.setValue_attribute(metricDetails.getValue_attribute());
        metric.setCount(metricDetails.getCount());
        metric.setCount_attribute(metricDetails.getCount_attribute());

        return metricRepository.save(metric);
    }
}
