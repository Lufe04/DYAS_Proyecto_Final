package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
@Service
public class AdvancedPlan implements TrainingPlanStrategy {

    @Override
    public void assignPlan(String email, TrainingPlanDAO trainingPlanDAO) {
        trainingPlanDAO.assignTrainingPlanToUser(email, "Advanced Plan", "2024-01-01", "2024-03-31");
    }
}




