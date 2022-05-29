package com.example.metricseditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class QueryController {

    @GetMapping("/editor")
    public String showQuery(Model model) {
        Query query = new Query();
        model.addAttribute("query", query);

        List<String> listDashboard = Arrays.asList("GitHub Index", "Taiga Index");
        model.addAttribute("dashboard", listDashboard);

        List<String> listMetric = Arrays.asList("Select Metric");
        model.addAttribute("metric", listMetric);

        List<String> listFunction = Arrays.asList("Select Function");
        model.addAttribute("function", listFunction);

        return "editor";
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String showSearchPage() { return "search";}
}

