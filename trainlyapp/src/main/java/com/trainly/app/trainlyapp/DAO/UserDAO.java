package com.trainly.app.trainlyapp.DAO;

import com.trainly.app.trainlyapp.services.User;
import com.trainly.app.trainlyapp.config.DatabaseConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDAO {

    @Autowired
    private DatabaseConfig databaseConfig;

    // Guardar usuario en la base de datos
    public boolean saveUser(User user) {
        String query = "INSERT INTO users (username, password, email, user_type) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserType());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     // Método para autenticación de usuarios (login)
public User loginUser(String email, String password) {
    // Modificar la consulta para buscar por email en lugar de username
    String query = "SELECT * FROM users WHERE email = ? AND password = ?";
    
    try (Connection connection = databaseConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        
        // Establecer los valores del email y la contraseña
        statement.setString(1, email);
        statement.setString(2, password);
        
        // Ejecutar la consulta
        ResultSet rs = statement.executeQuery();
        
        // Si hay un resultado, crear y devolver el objeto User
        if (rs.next()) {
            return new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("user_type")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Si no se encuentra el usuario, devolver null
    return null;
}
}


    


