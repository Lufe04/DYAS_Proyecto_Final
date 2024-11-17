package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.UserDAO;

public class Login {
    private final UserDAO userDAO;

    public Login(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    
    public User loginUser(String email, String password) {
        User user = userDAO.loginUser(email, password);
        return user ; // Devuelve true si el usuario existe, false en caso contrario
    }
}




