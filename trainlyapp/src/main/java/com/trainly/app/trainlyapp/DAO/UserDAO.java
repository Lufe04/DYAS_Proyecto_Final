package com.trainly.app.trainlyapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trainly.app.trainlyapp.config.DatabaseConfig;
import com.trainly.app.trainlyapp.models.UserEntity;

@Repository
public class UserDAO {

    @Autowired
    private DatabaseConfig databaseConfig;

    // Guardar usuario (cliente o entrenador) en la base de datos
    public boolean saveUser(UserEntity user) {
        System.out.println("Intentando guardar usuario: " + user.getUsername());
        
        if (!(user.getUserType().equalsIgnoreCase("TRAINER") || user.getUserType().equalsIgnoreCase("CLIENT"))) {
            System.out.println("Tipo de usuario inválido: " + user.getUserType());
            return false;
        }

        String query = "INSERT INTO users (username, password, email, user_type) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserType());
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Usuario guardado exitosamente");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
        return false;
    }

    // Método para autenticación de usuario (cliente o entrenador)
    public UserEntity loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Obtenemos los datos del usuario desde la base de datos
                String username = rs.getString("username");
                String userType = rs.getString("user_type");

                // Creamos y devolvemos un objeto UserEntity
                return new UserEntity(
                    username,
                    rs.getString("password"),
                    rs.getString("email"),
                    userType
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al autenticar usuario: " + e.getMessage());
        }
        // Si no se encuentra el usuario, devolvemos null
        return null;
    }
}
