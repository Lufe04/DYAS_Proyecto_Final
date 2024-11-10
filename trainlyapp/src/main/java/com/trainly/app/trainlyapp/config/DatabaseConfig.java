package com.trainly.app.trainlyapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/trainly"; // Cambia 'trainly' por el nombre de tu base de datos
    private static final String USER = "root"; // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "12345"; // Cambia por tu contraseña de MySQL

    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


