package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
import com.trainly.app.trainlyapp.DAO.TrainingTrackerDAO;
import com.trainly.app.trainlyapp.DAO.ProfileDAO;
import com.trainly.app.trainlyapp.services.TrainningPlan;
import com.trainly.app.trainlyapp.services.TrainningTracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrainingTest {

    public static void main(String[] args) {
        try {
            // Paso 1: Conectar a la base de datos
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trainly?useSSL=false&serverTimezone=UTC", 
                    "root", 
                    "12345");

            // Paso 2: Crear instancias de los DAOs
            TrainingPlanDAO trainingPlanDAO = new TrainingPlanDAO(connection);
            TrainingTrackerDAO trainingTrackerDAO = new TrainingTrackerDAO(connection);
            ProfileDAO profileDAO = new ProfileDAO(connection);

            // Paso 3: Asignar un plan de entrenamiento a un usuario
            String userEmail = "daniela@ejemplo.com";
            boolean planAssigned = trainingPlanDAO.assignTrainingPlanToUser(userEmail, 
                    "Plan de Fuerza", 
                    "2024-11-01", 
                    "2024-12-01");

            if (planAssigned) {
                System.out.println("Plan de entrenamiento asignado correctamente a: " + userEmail);
            } else {
                System.out.println("Error al asignar el plan de entrenamiento.");
            }

            // Paso 4: Agregar progreso para el plan de entrenamiento asignado
            // Obtenemos el user_id y el plan_id
            int userId = profileDAO.getUserIdByEmail(userEmail);
            int trainingPlanId = getTrainingPlanIdByUserId(connection, userId); // Método auxiliar

            if (trainingPlanId != -1) {
                boolean progressAdded = trainingTrackerDAO.addTrainingProgress(userId, 
                        trainingPlanId, 
                        "Progreso del entrenamiento: 80% completado");
                if (progressAdded) {
                    System.out.println("Progreso registrado correctamente.");
                } else {
                    System.out.println("Error al registrar el progreso.");
                }
            } else {
                System.out.println("No se encontró un plan de entrenamiento para el usuario.");
            }

            // Paso 5: Consultar los planes de entrenamiento y el progreso
            List<TrainningPlan> plans = profileDAO.getTrainingPlansForUser(userEmail);
            List<TrainningTracking> trackers = profileDAO.getTrainingProgressForUser(userEmail);

            // Mostrar los resultados
            System.out.println("\nPlanes de entrenamiento asignados:");
            for (TrainningPlan plan : plans) {
                System.out.println("ID: " + plan.getId() + ", Nombre: " + plan.getPlanName() +
                        ", Fechas: " + plan.getStartDate() + " - " + plan.getEndDate());
            }

            System.out.println("\nProgreso de entrenamiento:");
            for (TrainningTracking tracker : trackers) {
                System.out.println("ID: " + tracker.getId() + ", Plan ID: " + tracker.getTrainingPlanId() +
                        ", Progreso: " + tracker.getProgress() + ", Fecha: " + tracker.getDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para obtener el trainingPlanId basado en el userId
    private static int getTrainingPlanIdByUserId(Connection connection, int userId) {
        try {
            String query = "SELECT id FROM training_plans WHERE user_id = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // No se encontró un plan para este usuario
    }
}
