package com.trainly.app.trainlyapp.services;

import java.sql.Date;
import java.time.LocalDate;

public class TrainningPlan {
    private int id;
    private String planName;
    private Date startDate;
    private Date endDate;

    // Constructor con los parámetros (id, planName, startDate, endDate)
    public TrainningPlan(int id, String planName, Date startDate, Date endDate) {
        this.id = id;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return "TrainingPlan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

