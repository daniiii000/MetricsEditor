package com.example.metricseditor;

import com.example.metricseditor.files.FileOperations;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {


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
    public String showShowPage() {
        return "/show";
    }

    @GetMapping("/edit")
    public String showEditPage() {
        return "/edit";
    }

}
