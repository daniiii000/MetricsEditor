package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;


@Controller
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


    @GetMapping("/metrics")
    public ModelAndView getAllMetrics() {
        ModelAndView mav = new ModelAndView("editor");
        mav.addObject("metrics", metricService.getAllMetrics());
        return mav;
    }

    @PostMapping("/save")
    public String addMetric(@ModelAttribute Metric metric, @RequestParam("conditions") Set<Condition> conditions, @RequestParam("modifiers") Set<Modifier>modifiers) {
       /* Metric metric = new Metric();
        model.addAttribute("pattern", metric.getPattern());*/
        metric.setModifiers(modifiers);
        metric.setConditions(conditions);
        metricService.addMetric(metric);
        //conditions.getId().setMetric(metric);
        conditionService.addConditions(conditions);
        modifierService.addModifiers(modifiers);
        return "redirect:/metrics";

    }

    @GetMapping("/metrics/{id}")
    public ModelAndView getMetricById(Model model, @PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("show");
        //model.addAttribute("conditions",conditionService.getAllConditions());
        //model.addAttribute("modifiers",modifierService.getAllModifiers());
        mav.addObject("metric", metricService.getMetricById(metricId));
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getMetricByIdForEdit(Model model, @PathVariable(value = "id") Long metricId) throws ResourceNotFoundException {
        ModelAndView mav = new ModelAndView("edit");
        //model.addAttribute("conditions",conditionService.getAllConditions());
        //model.addAttribute("modifiers",modifierService.getAllModifiers());
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