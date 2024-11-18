package com.trainly.app.trainlyapp;


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
@Transactional
public class TrainingPlanServiceTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TrainingPlanDAO trainingPlanDAO;

    @Autowired
    private Login loginService; // Inyectamos el servicio de Login

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("Test User", "testuser@example.com", "password123", "user");
        userDAO.saveUser(testUser); // Guardamos el usuario de prueba en la base de datos
    }

    @Test
    public void testAssignTrainingPlanToUser() throws SQLException {
        // Usamos el servicio inyectado para hacer el login
        User loggedUser = loginService.login("daniela@ejemplo.com", "12345");
        assertNotNull(loggedUser, "El usuario debe estar autenticado");

        TrainningPlan plan = new TrainningPlan(
            1, 
            "Advanced Plan", 
            Date.valueOf("2024-01-01"),  
            Date.valueOf("2024-03-31")
        );

        boolean result = trainingPlanDAO.assignTrainingPlanToUser("daniela@ejemplo.com", "Advanced Plan", "2024-01-01", "2024-03-31");
        assertTrue(result, "El plan debe haberse asignado correctamente");

        TrainningPlan assignedPlan = trainingPlanDAO.getTrainingPlanByUserEmail("daniela@ejemplo.com");
        assertNotNull(assignedPlan, "El plan de entrenamiento debe estar guardado en la base de datos");
        assertEquals("Plan de Fuerza", assignedPlan.getPlanName(), "El nombre del plan debe ser 'Advanced Plan'");
    }

    @Test
    public void testInvalidLogin() {
        User invalidUser = loginService.login("invaliduser@example.com", "wrongpassword");
        assertNull(invalidUser, "El usuario no debería existir con estas credenciales");
    }
}
