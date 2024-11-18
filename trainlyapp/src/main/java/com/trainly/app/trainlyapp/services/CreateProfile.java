package com.trainly.app.trainlyapp.services;


import java.util.ArrayList;
import java.util.List;

public class CreateProfile {
    private String username;
    private String fullName;
    private String userType;
    private List<TrainningPlan> trainingPlans;
    private List<TrainningTracking> trainingTrackers;

    // Constructor, getters y setters
    public CreateProfile(String username, String fullName, String userType, List<TrainningPlan> trainingPlans, List<TrainningTracking> trainingTrackers) {
        this.username = username;
        this.fullName = fullName;
        this.userType = userType;
        this.trainingPlans = trainingPlans;
        this.trainingTrackers = trainingTrackers;
    }

    public CreateProfile(String username, String fullName, String userType) {
    this.username = username;
    this.fullName = fullName;
    this.userType = userType;
    this.trainingPlans = new ArrayList<>();
    this.trainingTrackers = new ArrayList<>();
}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<TrainningPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainningPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public List<TrainningTracking> getTrainingTrackers() {
        return trainingTrackers;
    }

    public void setTrainingTrackers(List<TrainningTracking> trainingTrackers) {
        this.trainingTrackers = trainingTrackers;
    }
}


