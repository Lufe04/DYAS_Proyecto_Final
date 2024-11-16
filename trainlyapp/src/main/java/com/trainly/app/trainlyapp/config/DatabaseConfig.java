package com.trainly.app.trainlyapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/trainly?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    @Bean
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar a la base de datos", e);
        }
    }
}
