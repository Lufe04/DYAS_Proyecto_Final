package com.trainly.app.trainlyapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainingTrackerDAO {

    private Connection connection;

    public TrainingTrackerDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addTrainingProgress(int userId, int trainingPlanId, String progress) {
        String insertTrackerQuery = "INSERT INTO training_tracker (user_id, training_plan_id, progress) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(insertTrackerQuery)) {
            statement.setInt(1, userId);
            statement.setInt(2, trainingPlanId);
            statement.setString(3, progress);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

