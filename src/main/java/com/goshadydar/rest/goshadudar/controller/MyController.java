package com.goshadydar.rest.goshadudar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyController {

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "MY home page");
        return "home";
    }
    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "Page and nas");
        return "about";
    }
}