package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/index")
    public String showHomePage() {
        return "/index";
    }

    @GetMapping("/search")
    public String showSearchPage() {
        return "/search";
    }

    @GetMapping("/assister")
    public String showAssisterPage() { return "/assister";}
}
