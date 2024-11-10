package com.trainly.app.trainlyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trainly.app.trainlyapp.services.CreateUser;
import com.trainly.app.trainlyapp.services.User;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private CreateUser userService;

    // Endpoint para el registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getUserType());
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario");
        }
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getEmail(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}

