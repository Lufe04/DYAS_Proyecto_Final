package com.trainly.app.trainlyapp.controllers;

import com.trainly.app.trainlyapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrainnignPlanController{
    private final TrainingPlanContext trainingPlanContext;
    private final BeginnerPlan beginnerPlan;
    private final IntermediatePlan intermediatePlan;
    private final AdvancedPlan advancedPlan;

    @Autowired
    public TrainnignPlanController(TrainingPlanContext trainingPlanContext, BeginnerPlan beginnerPlan,
                                  IntermediatePlan intermediatePlan, AdvancedPlan advancedPlan) {
        this.trainingPlanContext = trainingPlanContext;
        this.beginnerPlan = beginnerPlan;
        this.intermediatePlan = intermediatePlan;
        this.advancedPlan = advancedPlan;
    }

    @GetMapping("/assignPlan")
    public String showAssignPlanForm() {
        return "plans/AssignPlan"; // Vista para asignar planes
    }

    @PostMapping("/assignPlan")
    public String assignPlan(@RequestParam String email, @RequestParam String level, Model model) {
        switch (level.toLowerCase()) {
            case "beginner":
                trainingPlanContext.setStrategy(beginnerPlan);
                break;
            case "intermediate":
                trainingPlanContext.setStrategy(intermediatePlan);
                break;
            case "advanced":
                trainingPlanContext.setStrategy(advancedPlan);
                break;
            default:
                model.addAttribute("error", "Nivel no válido.");
                return "plans/AssignPlan";
        }

        trainingPlanContext.assignPlanToUser(email);
        model.addAttribute("message", "Plan asignado correctamente.");
        return "plans/AssignPlan";
    }
}
