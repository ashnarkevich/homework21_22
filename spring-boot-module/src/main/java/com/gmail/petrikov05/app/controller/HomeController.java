package com.gmail.petrikov05.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String getWelcomePage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "/index";
    }

}
