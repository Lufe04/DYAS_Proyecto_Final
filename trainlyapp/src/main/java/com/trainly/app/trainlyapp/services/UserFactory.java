package com.trainly.app.trainlyapp.services;

import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.models.UserEntity;

@Service
public class UserFactory {

    /**
     * Método para crear un objeto UserEntity basado en el rol.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @param email    El correo electrónico.
     * @param role     El rol del usuario (TRAINER o CLIENT).
     * @return Una instancia de UserEntity correspondiente al rol.
     * @throws IllegalArgumentException si el tipo de usuario no es válido.
     */
    public UserEntity createUser(String username, String password, String email, String role) {
        // Normalizar el rol para evitar problemas de comparación
        String normalizedRole = role.trim().toUpperCase();

        switch (normalizedRole) {
            case "CLIENT":
                return new UserEntity(username, password, email, "CLIENT");
            case "TRAINER":
                return new UserEntity(username, password, email, "TRAINER");
            default:
                throw new IllegalArgumentException("Tipo de usuario desconocido: " + role);
        }
    }
}
