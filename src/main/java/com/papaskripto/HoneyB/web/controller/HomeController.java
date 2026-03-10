package com.papaskripto.HoneyB.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/home")
public class HomeController {
    @GetMapping
    public String Index () {
        return "home";
    }
}
