package com.trainly.app.trainlyapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.config.DatabaseConfig;
import com.trainly.app.trainlyapp.controllers.UserController;
import com.trainly.app.trainlyapp.services.User;


@SpringBootTest
class TrainlyappApplicationTests {
    @Autowired
    private UserController userController;
    
	@Test
	void contextLoads() {
	}

    private Connection connection;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Obtener una nueva conexión a la base de datos
        connection = DatabaseConfig.getConnection();
        userDAO = new UserDAO(connection);
    }

    @Test
    public void testSaveUser() {
        // Crear un objeto User con los datos del nuevo usuario
        User newUser = new User("newuser@example.com", "Nuevo Usuario", "password123", "basic");

        // Llamar al método para guardar el usuario
        boolean isSaved = userDAO.saveUser(newUser);

        // Verificar que el usuario se insertó correctamente
        assertTrue(isSaved, "El usuario no fue agregado correctamente.");

        // Verificar que el usuario existe en la base de datos
        boolean userExists = userDAO.isUserExist(newUser.getEmail());
        assertTrue(userExists, "El usuario no fue encontrado en la base de datos.");
    }

    // Método para limpiar los datos después de cada prueba
    @AfterEach
    public void cleanUp() throws SQLException {
        String email = "newuser@example.com"; // El email que usamos para agregar el usuario

        // Eliminar el usuario después de la prueba
        String deleteQuery = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }

	public static void main(String[] args) {
        // Inicializa el contexto de Spring Boot
        ApplicationContext context = SpringApplication.run(TrainlyappApplication.class, args);

        // Obtén el bean UserController del contexto
        UserController userController = context.getBean(UserController.class);

        // Crea un objeto User
        User user = new User();
        user.setUsername("Juan");
        user.setPassword("12345");
        user.setEmail("juan@ejemplo.com");
        user.setUserType("client");

        // Realiza el registro del usuario
        var response = userController.registerUser(user);
        System.out.println(response.getBody());

    
     
    }

    /*public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TrainlyappApplication.class, args);
        UserController userController = context.getBean(UserController.class);

        // Prueba de inicio de sesión con credenciales válidas
        testLogin(userController, "daniela@ejemplo.com", "12345");

        // Prueba de inicio de sesión con credenciales inválidas
        testLogin(userController, "invalidUser ", "wrongPassword");
    }

    private static void testLogin(UserController userController, String email, String password) {
        // Crea un objeto User
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // Realiza el inicio de sesión
        ResponseEntity<String> response = userController.loginUser (user);

        // Imprime el resultado de la prueba
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Login successful for user: " + email);
        } else {
            System.out.println("Login failed for user: " + email + " - " + response.getBody());
        }
    }*/
}
