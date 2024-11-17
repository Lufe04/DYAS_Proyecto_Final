package com.trainly.app.trainlyapp.services;

import java.sql.Timestamp;

public class TrainningTracking {
    private int id;
    private int trainingPlanId;
    private String progress;
    private Timestamp date;

    // Constructor, getters y setters
    public TrainningTracking(int id, int trainingPlanId, String progress, Timestamp date) {
        this.id = id;
        this.trainingPlanId = trainingPlanId;
        this.progress = progress;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "TrainingTracker{" +
                "id=" + id +
                ", trainingPlanId=" + trainingPlanId +
                ", progress='" + progress + '\'' +
                ", date=" + date +
                '}';
    }
}
