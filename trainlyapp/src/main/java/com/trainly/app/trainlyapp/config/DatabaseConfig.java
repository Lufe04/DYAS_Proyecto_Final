package com.trainly.app.trainlyapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    private static String url = "jdbc:mysql://localhost:3306/trainly?useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "12345";

    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}