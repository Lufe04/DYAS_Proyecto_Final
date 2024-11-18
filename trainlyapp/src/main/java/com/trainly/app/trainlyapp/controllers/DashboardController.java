package com.trainly.app.trainlyapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html"; // Esto debe coincidir con el nombre del archivo dashboard.html en templates
    }
}