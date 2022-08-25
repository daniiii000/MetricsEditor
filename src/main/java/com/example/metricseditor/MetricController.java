package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class MetricController {

    @Autowired
    @Qualifier("metricservice")
    private MetricService metricService;


    @GetMapping("/metrics")
    public ModelAndView getAllMetrics() {
        ModelAndView mav = new ModelAndView("editor");
        mav.addObject("metrics", metricService.getAllMetrics());
        return mav;
    }

    @PostMapping("/metrics")
    public String addMetric(@RequestBody Metric metric) {
        metricService.addMetric(metric);
        return "redirect:/metrics";

    }

    @GetMapping("/metrics/{id}")
    public ModelAndView getMetricById(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("show");
        mav.addObject("metric", metricService.getMetricById(metricId));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteMetric(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        metricService.deleteMetric(metricId);
        return "redirect:/metrics";
    }

    @PutMapping("/metrics/{id}")
    public String updateMetric(@PathVariable(value = "id") Long metricId, @Valid @RequestBody Metric metricDetails) throws ResourceNotFoundException {
        metricService.updateMetric(metricId, metricDetails);

        return "redirect:/metrics";
    }
}