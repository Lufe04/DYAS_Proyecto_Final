package com.trainly.app.trainlyapp.controllers;

import com.trainly.app.trainlyapp.models.UserEntity;
import com.trainly.app.trainlyapp.services.Login;

public class LoginController {
    private final Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    public String login(String email, String password) {
        UserEntity user = login.loginUser(email, password);
        if (user != null) {
            // Usuario autenticado correctamente
            return "redirect:/dashboard";
        } else {
            // Credenciales inválidas
            return "Invalid username or password";
        }
    }
    
}
