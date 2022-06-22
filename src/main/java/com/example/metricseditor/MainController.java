package com.example.metricseditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/editor")
    public String showQuery(Model model) {
        Metric metric = new Metric(1L, "Percentage of closed tasks", "Percentage","Individual","Informative","Overall","Tasks",null, null, "state", "closed", null, null, null, null);
        model.addAttribute("metric", metric);

        return "/editor";
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
