package com.trainly.app.trainlyapp.services;

public class Client extends User {
    public Client(String username, String password, String email, String userType) {
        super(username, password, email, userType);
    }

    @Override
    public void displayInfo() {
        System.out.println("Cliente: " + getUsername());
    }
}

