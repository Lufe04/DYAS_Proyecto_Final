package com.trainly.app.trainlyapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trainly.app.trainlyapp.services.User;

public class TrainingPlanDAO {

    private Connection connection;

    // Constructor que acepta una conexión
    public TrainingPlanDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean assignTrainingPlanToUser(String email, String planName, String startDate, String endDate) {
        // Paso 1: Obtener el user_id del usuario con el email dado
        int userId = getUserIdByEmail(email);
        
        if (userId == -1) {
            System.out.println("Usuario no encontrado con el email: " + email);
            return false; // No se encontró el usuario
        }
    
        // Paso 2: Insertar el nuevo plan de entrenamiento
        String insertPlanQuery = "INSERT INTO training_plans (user_id, plan_name, start_date, end_date) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(insertPlanQuery)) {
            statement.setInt(1, userId); // Usamos el userId que obtuvimos con el email
            statement.setString(2, planName);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Método para obtener un objeto User completo por email
    public User getUserByEmail(String email) {
        String query = "SELECT id, username, password, email, user_type FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("user_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Método para obtener el ID del usuario usando su email
    public int getUserIdByEmail(String email) {
        String query = "SELECT id FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el usuario
    }


    public boolean addTrainingProgress(int userId, int trainingPlanId, String progress) {
        // Verifica que el userId es un valor entero y no un email
        String insertProgressQuery = "INSERT INTO training_tracker (user_id, training_plan_id, progress) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(insertProgressQuery)) {
            statement.setInt(1, userId);  // Asegúrate de pasar el ID numérico
            statement.setInt(2, trainingPlanId);
            statement.setString(3, progress);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
}
}