package com.trainly.app.trainlyapp.services;


import com.trainly.app.trainlyapp.DAO.NotificationDAO;
import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
import com.trainly.app.trainlyapp.services.User;
import java.sql.Connection;

public class NotificationManager {
    private NotificationService notificationService;
    private TrainingPlanDAO trainingPlanDAO;
    private NotificationDAO notificationDAO;

    public NotificationManager(NotificationService notificationService, TrainingPlanDAO trainingPlanDAO, NotificationDAO notificationDAO) {
        this.notificationService = notificationService;
        this.trainingPlanDAO = trainingPlanDAO;
        this.notificationDAO = notificationDAO;
    }

    
    // Método para asignar un plan y notificar al usuario
    public void assignTrainingPlanAndNotify(String email, String planName, String startDate, String endDate) {
        User user = trainingPlanDAO.getUserByEmail(email);
        if (user != null) {
            boolean planAssigned = trainingPlanDAO.assignTrainingPlanToUser(user.getEmail(), planName, startDate, endDate);
            if (planAssigned) {
                String message = "Nuevo plan de entrenamiento asignado: " + planName;
                
                // Enviar la notificación
                notificationService.registerObserver(user);
                notificationService.sendNotification(message);
                notificationService.removeObserver(user);
                
                // Guardar la notificación en la base de datos
                notificationDAO.saveNotification(user.getId(), message);
            }
        } else {
            System.out.println("Usuario no encontrado para el email: " + email);
        }
    }

    // Método para registrar progreso y notificar al usuario
    public void addProgressAndNotify(String email, int trainingPlanId, String progress) {
        int userId = trainingPlanDAO.getUserIdByEmail(email);
        boolean progressAdded = trainingPlanDAO.addTrainingProgress(userId, trainingPlanId, progress);
        if (progressAdded) {
            String message = "Progreso actualizado para tu plan de entrenamiento.";
            
            // Enviar la notificación
            User user = new User(email);
            notificationService.registerObserver(user);
            notificationService.sendNotification(message);
            notificationService.removeObserver(user);
            
            // Guardar la notificación en la base de datos
            notificationDAO.saveNotification(userId, message);
        }
    }
}


