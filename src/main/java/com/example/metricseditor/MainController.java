package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MetricRepository metricRepository;

    @GetMapping("/editor")
    public List<Metric> showMetrics(Model model) {
        List<Metric> metrics = metricRepository.findAll();
        model.addAttribute("metrics", metrics);

        return metrics;
    }

    @GetMapping("/delete/{id}")
    public String deleteMetric(@PathVariable(value = "id") Long metricId) {
        metricRepository.deleteById(metricId);

        return "redirect:/editor";
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "/index";
    }

    @GetMapping("/search")
    public String showSearchPage() {
        return "/search";
    }

    @GetMapping("/assister")
    public String showAssisterPage() {
        return "/assister";
    }

    @GetMapping("/show")
    public String showshowPage() {
        return "/show";
    }
}
