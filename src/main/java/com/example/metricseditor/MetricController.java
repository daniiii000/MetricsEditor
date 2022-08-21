package com.example.metricseditor;

import ch.qos.logback.classic.html.HTMLLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/")
public class MetricController {

    @Autowired
    private MetricRepository metricRepository;

    //get metrics
    @GetMapping("metrics")
    public List<Metric> getAllMetrics(Model model) {
        List<Metric> metrics = metricRepository.findAll();
        model.addAttribute("metrics", metrics);
        return metrics;
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
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<Metric> deleteMetric(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));
        metricRepository.deleteById(metricId);

        return ResponseEntity.ok().body(metric);

    }*/
}