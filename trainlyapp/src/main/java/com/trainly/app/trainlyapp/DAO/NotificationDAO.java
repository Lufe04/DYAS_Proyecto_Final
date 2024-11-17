package com.trainly.app.trainlyapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NotificationDAO {
    private Connection connection;

    public NotificationDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para guardar una notificación en la base de datos
    public boolean saveNotification(int userId, String message) {
        String insertQuery = "INSERT INTO notifications (user_id, message, created_at) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, userId);
            statement.setString(2, message);
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));  // Fecha y hora actual
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
