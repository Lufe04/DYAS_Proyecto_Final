package com.trainly.app.trainlyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainly.app.trainlyapp.DAO.UserDAO;
import com.trainly.app.trainlyapp.models.UserEntity;
import com.trainly.app.trainlyapp.services.UserFactory;

@Controller
public class UserController {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email,
                               @RequestParam String password, @RequestParam String userType) {
        UserEntity user = userFactory.createUser(username, password, email, userType);
        if (userDAO.saveUser(user)) {
            return "redirect:/login"; // Redirigir a la página de login si el registro fue exitoso
        }
        return "error"; // Redirigir a una página de error en caso de fallo
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        UserEntity user = userDAO.loginUser(email, password); // Usamos el método correcto del DAO
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
