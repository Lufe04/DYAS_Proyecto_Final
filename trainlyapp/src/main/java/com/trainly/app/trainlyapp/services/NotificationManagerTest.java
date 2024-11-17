package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.NotificationDAO;
import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
import com.trainly.app.trainlyapp.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class NotificationManagerTest {
    public static void main(String[] args) {
        // Configurar la base de datos y las dependencias
        DatabaseConfig dbConfig = new DatabaseConfig();
        try (Connection connection = dbConfig.getConnection()) {
            // Crear objetos DAO y servicio
            TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAO(connection);
            NotificationDAO notificationDAO = new NotificationDAO(connection);
            NotificationService notificationService = new NotificationService();
            NotificationManager notificationManager = new NotificationManager(notificationService, trainingPlanDAO, notificationDAO);
            
            // Obtener el ID del usuario usando su email
            String email = "daniela@ejemplo.com"; 
            int userId = trainingPlanDAO.getUserIdByEmail(email);
            
            if (userId != -1) {
                // Asignar un plan de entrenamiento y notificar al usuario
                String planName = "Plan Básico";
                String startDate = "2024-01-01";
                String endDate = "2024-12-31";
                notificationManager.assignTrainingPlanAndNotify(email, planName, startDate, endDate);
                
                // Agregar progreso y notificar
                int trainingPlanId = 1; // Suponiendo que el plan tiene ID 1
                String progress = "Completado el primer mes";
                notificationManager.addProgressAndNotify(email, trainingPlanId, progress);
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
