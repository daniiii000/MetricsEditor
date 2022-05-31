package com.example.metricseditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QueryController {


    @GetMapping("/editor")
    public String showQuery(Model model) {
        Query query = new Query(1L, "GitHub Index", "Big Commits", "Maximum");
        model.addAttribute("query", query);

        List<String> listIndex = Arrays.asList("GitHub Index", "Taiga Index");
        model.addAttribute("index", listIndex);

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

