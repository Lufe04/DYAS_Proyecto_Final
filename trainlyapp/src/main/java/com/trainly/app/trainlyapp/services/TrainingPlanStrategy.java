package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;

public interface TrainingPlanStrategy {
    void assignPlan(String email, TrainingPlanDAO trainingPlanDAO);
}
