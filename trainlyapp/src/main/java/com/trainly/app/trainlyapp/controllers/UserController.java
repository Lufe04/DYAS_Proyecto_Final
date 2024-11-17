// Endpoint para el registro de usuarios
package com.trainly.app.trainlyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainly.app.trainlyapp.responses.LoginResponse;
import com.trainly.app.trainlyapp.services.CreateUser ;
import com.trainly.app.trainlyapp.services.User;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private CreateUser  userService;

    // Endpoint para el registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody User user) {
        System.out.println("Entra a registerUser ");
        System.out.println("Usuario: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Tipo de usuario: " + user.getUserType());

        boolean isRegistered = userService.registerUser (user.getUsername(), user.getPassword(), user.getEmail(), user.getUserType());
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario");
        }
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser (@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getEmail(), user.getPassword());
        if (isAuthenticated) {
            System.out.println("entra a LoginUser, antes del HTML");
            return ResponseEntity.ok(new LoginResponse("Inicio de sesión exitoso", "/dashboard.html"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Credenciales incorrectas\"}");
        }
    }
}
