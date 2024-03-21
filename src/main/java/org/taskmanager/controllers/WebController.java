package org.taskmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class WebController {

    @GetMapping("/")
    public String homePage() {
        return "homePage.html";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signUp.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }
}
