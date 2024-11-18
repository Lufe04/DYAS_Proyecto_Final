package com.trainly.app.trainlyapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        userDAO = new UserDAO();
    }

    @Test
    public void testSaveUser() {
        // Crear un objeto User con los datos del nuevo usuario
        User newUser = new User("User", "password123 ", "user@gmail.com", "CLIENT");

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
    @Test
    public void cleanUp() throws SQLException {
        String email = "newuser@example.com"; // El email que usamos para agregar el usuario

        // Eliminar el usuario después de la prueba
        String deleteQuery = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }
    @Test
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
   
   
}
