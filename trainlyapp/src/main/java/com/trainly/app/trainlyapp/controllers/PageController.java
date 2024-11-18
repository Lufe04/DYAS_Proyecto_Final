package com.trainly.app.trainlyapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/user-view")
    public String userView() {
        return "user/UserView"; // No necesitas ".html" si está en templates con Thymeleaf
    }

    @GetMapping("/training-plan-view")
    public String trainingPlanView() {
        return "user/TrainningPlanView.html"; // Coincide con el nombre del archivo en templates
    }

    @GetMapping("/notification-view")
    public String notificationView() {
        return "user/NotificationView";
    }

    @GetMapping("/payment-view")
    public String paymentView() {
        return "user/PaymentView.html";
    }
}