package com.trainly.app.trainlyapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @GetMapping("/current")
    public UserProfile getCurrentUserProfile(Authentication authentication) {
        String username = authentication.getName(); // Nombre del usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // Detalles del usuario

        // Crear y devolver un objeto con los datos del usuario
        return new UserProfile(
            username,
            userDetails.getAuthorities().toString(), // Roles del usuario
            "Nombre Completo Simulado" // Puedes obtenerlo de la base de datos si es necesario
        );
    }

    // Clase interna para representar los datos del perfil del usuario
    public static class UserProfile {
        private String username;
        private String user_type;
        private String email;

        public UserProfile(String username, String user_type, String email) {
            this.username = username;
            this.user_type = user_type;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getUserType() {
            return user_type;
        }

        public String getEmail() {
            return email;
        }
    }
}