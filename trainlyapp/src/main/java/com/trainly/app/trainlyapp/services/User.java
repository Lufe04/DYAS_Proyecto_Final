package com.trainly.app.trainlyapp.services;


public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String userType;  // Nuevo campo para el tipo de usuario

    // Constructor vacío
    public User() {}

    // Constructor completo
    public User(String username, String password, String email, String userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
