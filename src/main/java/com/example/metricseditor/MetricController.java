package com.example.metricseditor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



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
    public String addMetric(HttpServletRequest request) {

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

        Metric metric = new Metric(name, pattern,subject,type,teamextension,object,null,null,null);
        metricRepository.save(metric);

        Condition condition = new Condition(condition_type, condition_attribute);
        condition.setMetric(metric);
        conditionRepository.save(condition);
        Modifier modifier = new Modifier(modifier_type,modifier_attribute);
        modifier.setMetric(metric);
        modifierRepository.save(modifier);
        List<Condition> conditions_list = new ArrayList<>();
        conditions_list.add(condition);

        List<Modifier> modifiers_list = new ArrayList<>();
        modifiers_list.add(modifier);
        metric.setConditions(conditions_list);
        metric.setModifiers(modifiers_list);

        metricRepository.save(metric);

        return "redirect:/metrics";

    }

    @GetMapping("/metrics/{id}")
    public ModelAndView getMetricById(@PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("show");
        mav.addObject("metric", metricService.getMetricById(metricId));
        mav.addObject("conditions", conditionService.getConditions(metricId));
        mav.addObject("modifiers", modifierService.getModifiers(metricId));
        return mav;
    }

    @GetMapping("/delete/{id}")
    public void deleteMetric(@PathVariable(value = "id") Long metricId, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        metricService.deleteMetric(metricId);
        response.sendRedirect("http://localhost:8080/metrics");

    }

    @PutMapping("/metrics/{id}")
    public String updateMetric(@PathVariable(value = "id") Long metricId, @Valid @RequestBody Metric metricDetails) throws ResourceNotFoundException {
        metricService.updateMetric(metricId, metricDetails);

        return "redirect:/metrics";
    }
}