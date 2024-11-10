package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.UserDAO;

@Service
public class CreateUser {
    private UserDAO userDAO = new UserDAO();



        if (userType.equalsIgnoreCase("client")) {
            factory = new CreateClientFactory();
        } else if (userType.equalsIgnoreCase("trainer")) {
            factory = new CreateTrainerFactory();
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido");
        }

        User user = factory.createProfile(userType);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return userDAO.registerUser(user);
    }
}