package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.config.DatabaseConfig;
import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.services.User;
import com.trainly.app.trainlyapp.services.Login;

import java.sql.Connection;
public class LoginTest {
    public static void main(String[] args) throws Exception {
        Connection connection = DatabaseConfig.getConnection();
        Login loginService = new Login(connection);

        String email = "daniela@ejemplo.com";
        String password = "12345";

        User user = loginService.login(email, password);
        if (user != null) {
            System.out.println("Usuario autenticado: " + user.getEmail());
        } else {
            System.out.println("Credenciales inválidas o usuario no encontrado.");
        }
    }
}


