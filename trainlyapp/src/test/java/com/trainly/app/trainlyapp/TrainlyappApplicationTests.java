package com.trainly.app.trainlyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import com.trainly.app.trainlyapp.controllers.UserController;
import com.trainly.app.trainlyapp.services.User;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.trainly.app.trainlyapp.TrainlyappApplication;



@SpringBootTest
class TrainlyappApplicationTests {

    @Autowired
    private UserController userController;
    
	@Test
	void contextLoads() {
	}

	/*public static void main(String[] args) {
        // Inicializa el contexto de Spring Boot
        ApplicationContext context = SpringApplication.run(TrainlyappApplication.class, args);

        // Obtén el bean UserController del contexto
        UserController userController = context.getBean(UserController.class);

        // Crea un objeto User
        User user = new User();
        user.setUsername("Daniela");
        user.setPassword("12345");
        user.setEmail("daniela@ejemplo.com");
        user.setUserType("client");

        // Realiza el registro del usuario
        var response = userController.registerUser(user);
        System.out.println(response.getBody());

    
     
    }*/

    public static void main(String[] args) {
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
    }
}
