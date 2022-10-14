package com.example.metricseditor.controllers;


import com.example.metricseditor.exceptions.ResourceNotFoundException;
import com.example.metricseditor.models.Metric;
import com.example.metricseditor.repositories.ConditionRepository;
import com.example.metricseditor.repositories.MetricRepository;
import com.example.metricseditor.repositories.ModifierRepository;
import com.example.metricseditor.services.ConditionService;
import com.example.metricseditor.services.MetricService;
import com.example.metricseditor.services.ModifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@RestController
@RequestMapping("/")
public class MetricController {

    @Autowired
    @Qualifier("metricservice")
    private MetricService metricService;

    @Autowired
    @Qualifier("conditionservice")
    private ConditionService conditionService;

    @Autowired
    @Qualifier("modifierservice")
    private ModifierService modifierService;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private ModifierRepository modifierRepository;


    @GetMapping("/metrics")
    public ModelAndView getAllMetrics() {
        ModelAndView mav = new ModelAndView("editor");
        mav.addObject("metrics", metricService.getAllMetrics());
        return mav;
    }

    @PostMapping("/save")
    public String addMetric(HttpServletRequest request) throws IOException {
        metricService.addMetric(request);
        return "redirect:/metrics";

    }

    @GetMapping("/metrics/{id}")
    public ModelAndView getMetricById(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("show");
        mav.addObject("metric", metricService.getMetricById(metricId));
       /* mav.addObject("conditions", conditionService.getConditions(metricId));
        mav.addObject("modifiers", modifierService.getModifiers(metricId));*/
        return mav;
    }

    @GetMapping("/showProperties/{id}")
    public ModelAndView getFileProperties(@PathVariable(value = "id") Long metricId) throws Exception {
        ModelAndView mav = new ModelAndView("showProperties");
        mav.addObject("properties", metricService.getPropertiesFile(metricId));
        return mav;
    }

    @GetMapping("/showQuery/{id}")
    public ModelAndView getFileQuery(@PathVariable(value = "id") Long metricId) throws Exception {
        ModelAndView mav = new ModelAndView("showQuery");
        mav.addObject("query", metricService.getQueryFile(metricId));
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getMetricForUpdate(@PathVariable (value = "id") Long metricId) throws ResourceNotFoundException, IOException {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("metric", metricService.getMetricById(metricId));
        mav.addObject("metric_id", metricId);

        return mav;
    }

    @GetMapping("/delete/{id}")
    public void deleteMetric(@PathVariable(value = "id") Long metricId, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        metricService.deleteMetric(metricId);
        response.sendRedirect("http://localhost:8080/metrics");

    }

    @GetMapping("/update")
    public void updateMetric(HttpServletRequest request, String name_before_edit) throws ResourceNotFoundException, IOException {
        metricService.updateMetric(request,name_before_edit);
    }
}