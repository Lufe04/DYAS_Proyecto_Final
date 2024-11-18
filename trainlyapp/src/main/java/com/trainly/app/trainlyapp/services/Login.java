package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.UserDAO;

import com.trainly.app.trainlyapp.services.User;
import org.springframework.stereotype.Service;
import java.sql.Connection;

@Service
public class Login implements ILoginService {
    private final UserDAO userDAO;

    // Constructor que recibe una conexión para inicializar UserDAO
    public Login(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    // Método de autenticación que implementa la interfaz
    @Override
    public User login(String email, String password) {
        // Consultar la base de datos para autenticar al usuario
        User user = userDAO.loginUser(email, password);
        if (user != null) {
            System.out.println("Usuario autenticado: " + user.getEmail());
        } else {
            System.out.println("Usuario no encontrado o credenciales incorrectas.");
        }
        return user;
    }
}






