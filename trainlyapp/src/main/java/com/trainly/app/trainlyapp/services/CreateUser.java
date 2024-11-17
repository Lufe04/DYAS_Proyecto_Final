package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.services.User;

@Service
public class CreateUser {
    private UserDAO userDAO = new UserDAO(null);

    public boolean registerUser(String username, String password, String email, String userType) {
        User user = new User(username, password, email, userType);
        return userDAO.saveUser(user);
    }
    
    // Método para autenticar al usuario
    public boolean authenticate(String email, String password) {
        User user = userDAO.loginUser(email, password);
        return user != null;

    }
}
