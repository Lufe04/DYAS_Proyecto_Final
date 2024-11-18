package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
@Service
public class TrainingPlanContext {
    private TrainingPlanStrategy strategy;
    private final TrainingPlanDAO trainingPlanDAO;

    public TrainingPlanContext(TrainingPlanDAO trainingPlanDAO) {
        this.trainingPlanDAO = trainingPlanDAO;
    }

    public void setStrategy(TrainingPlanStrategy strategy) {
        this.strategy = strategy;
    }

    public void assignPlanToUser(String email) {
        if (strategy != null) {
            strategy.assignPlan(email, trainingPlanDAO);
        }
    }
}

