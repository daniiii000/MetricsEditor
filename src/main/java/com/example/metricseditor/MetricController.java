package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController 
@RequestMapping("/")
public class MetricController {

    @Autowired
    private MetricRepository metricRepository;

    //get metrics
    @GetMapping("metrics")
    public List<Metric> getAllMetrics() {
        return this.metricRepository.findAll();
    }

    //get metric by id
    @GetMapping("/metrics/{id}")
    public ResponseEntity<Metric> getMetricById(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        return ResponseEntity.ok().body(metric);
    }

    //create metric
    @PostMapping("metrics")
    public Metric createMetric(@RequestBody Metric metric) {
        return this.metricRepository.save(metric);
    }

    //update metric
    @PutMapping("metrics/{id}")
    public ResponseEntity<Metric> updateMetric(@PathVariable(value = "id") Long metricId, @Valid @RequestBody Metric metricDetails) throws ResourceNotFoundException {
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

        return ResponseEntity.ok(this.metricRepository.save(metric));
    }


    //delete metric
    @DeleteMapping("metrics/{id}")
    public Map<String, Boolean> deleteMetric(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        this.metricRepository.delete(metric);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}