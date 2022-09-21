package com.example.metricseditor;


import com.example.metricseditor.files.FileOperations;
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
    public String addMetric(HttpServletRequest request) throws IOException {

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

        FileOperations.createProperties(name, description, object, pattern, subject, teamextension, conditions_list);
        FileOperations.createQueries(name, object, pattern, modifiers_list, conditions_list, subject, teamextension);

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
       /* mav.addObject("conditions", conditionService.getConditions(metricId));
        mav.addObject("modifiers", modifierService.getModifiers(metricId));*/
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getMetricForUpdate(@PathVariable (value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("metric", metricService.getMetricById(metricId));
        mav.addObject("id", metricId);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public void deleteMetric(@PathVariable(value = "id") Long metricId, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        metricService.deleteMetric(metricId);
        response.sendRedirect("http://localhost:8080/metrics");

    }

    @PutMapping("/update/{id}")
    public String updateMetric(@PathVariable(value = "id") Long metricId, HttpServletRequest request) throws ResourceNotFoundException {
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

        Metric metric = metricRepository.findById(metricId).orElseThrow(() -> new ResourceNotFoundException("Metric not found for this id :: " + metricId));

        metric.setName(name);
        metric.setPattern(pattern);
        metric.setSubject(subject);
        metric.setType(type);
        metric.setTeamextension(teamextension);
        metric.setObject(object);

        metricRepository.save(metric);

        return "redirect:/metrics";
    }
}