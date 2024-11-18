package com.trainly.app.trainlyapp;

/* 
import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.DAO.TrainingPlanDAO;
import com.trainly.app.trainlyapp.services.User;
import com.trainly.app.trainlyapp.services.Login;
import com.trainly.app.trainlyapp.services.TrainningPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootTest
public class TrainingPlanServiceTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TrainingPlanDAO trainingPlanDAO;

    @Autowired
    private Login loginService; // Suponiendo que tienes un servicio de login como el que has mencionado.

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Preparar datos antes de cada prueba
        testUser = new User("Test User", "testuser@example.com", "password123", "user");
        userDAO.saveUser(testUser); // Guardamos el usuario de prueba en la base de datos
    }

    @Test
    public void testAssignTrainingPlanToUser() throws SQLException {
        // Simulamos el login del usuario
        User loggedUser = Login.login("testuser@example.com", "password123");
        assertNotNull(loggedUser, "El usuario debe estar autenticado");

        // Crear un plan de entrenamiento
        TrainningPlan plan = new TrainningPlan(
            1, 
            "Advanced Plan", 
            Date.valueOf("2024-01-01"),  // Convierte la cadena a java.sql.Date
            Date.valueOf("2024-03-31") 
            );
        // Asignar el plan al usuario por correo
        boolean result = trainingPlanDAO.assignTrainingPlanToUser("testuser@example.com", "Advanced Plan", "2024-01-01", "2024-03-31");

        // Verificar que la asignación fue exitosa
        assertTrue(result, "El plan debe haberse asignado correctamente");
        
        // Verificar si el plan de entrenamiento se ha guardado en la base de datos
        TrainningPlan assignedPlan = trainingPlanDAO.getTrainingPlanByUserEmail("daniela@ejemplo.com");
        assertNotNull(assignedPlan, "El plan de entrenamiento debe estar guardado en la base de datos");
        assertEquals("Advanced Plan", assignedPlan.getPlanName(), "El nombre del plan debe ser 'Advanced Plan'");
    }

    @Test
    public void testInvalidLogin() {
        // Intentar logear con credenciales incorrectas
        User invalidUser = Login.login("invaliduser@example.com", "wrongpassword");
        assertNull(invalidUser, "El usuario no debería existir con estas credenciales");
    }
}
*/