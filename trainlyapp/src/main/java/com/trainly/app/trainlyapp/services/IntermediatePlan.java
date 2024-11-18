package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntermediatePlan implements TrainingPlanStrategy {
    @Override
    public void assignPlan(String email, TrainingPlanDAO trainingPlanDAO) {
        trainingPlanDAO.assignTrainingPlanToUser(email, "Intermediate Plan", "2024-01-01", "2024-03-31");
    }
}




