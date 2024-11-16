package com.trainly.app.trainlyapp.services;

public class Trainer extends User {
    public Trainer(String username, String password, String email, String userType) {
        super(username, password, email, userType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Entrenador: " + getUsername());
    }
}