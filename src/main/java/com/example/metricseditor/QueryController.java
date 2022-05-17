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

        List<String> listDashboard = Arrays.asList("GitHub Index, Taiga Index");
        model.addAttribute("listDashboard", listDashboard);

        return "editor";
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String showSearchPage() { return "search";}
}
