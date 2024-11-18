package com.trainly.app.trainlyapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.UserDAO;

@Service
public class CreateUser  {

    @Autowired
    private UserDAO userDAO; // Inyección de UserDAO

    public boolean registerUser (String username, String password, String email, String userType) {
        if (userDAO.isUserExist(email)) {
            return false; // El usuario ya existe
        }
        User user = new User(username, password, email, userType);
        return userDAO.saveUser (user);
    }

    // Método para autenticar al usuario
    public boolean authenticate(String email, String password) {
        User user = userDAO.loginUser (email, password);
        return user != null;
    }
}