package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.models.UserEntity;

public class Login {
    private final UserDAO userDAO;

    public Login(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public UserEntity loginUser(String email, String password) {
        UserEntity user = userDAO.loginUser(email, password);
        return user ; // Devuelve true si el usuario existe, false en caso contrario
    }
}




