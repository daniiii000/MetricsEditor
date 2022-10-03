package com.example.metricseditor.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping("/index")
    public String showHomePage() {
        return "homepage";
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

    @GetMapping("/help")
    public String showHelpPage() {
        return "/help";
    }

}
