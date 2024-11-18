package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.services.Observer;

public class User implements Observer {
    private int id;
    private String username;
    private String password;
    private String email;
    private String userType;

    // Constructor vacío
    public User() {}

    // Constructor completo
    public User(String username, String password, String email, String userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }
// Constructor que acepta solo email
public User(String email) {
    this.email = email;
}
    // Constructor con ID incluido
    public User(int id, String username, String password, String email, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    // Método del patrón Observer para recibir notificaciones
    @Override
    public void update(String message) {
        System.out.println("Notificación para " + username + " (" + email + "): " + message);
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

    // Método para mostrar información del usuario
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
    
}
