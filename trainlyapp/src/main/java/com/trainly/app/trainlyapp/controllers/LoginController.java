package com.trainly.app.trainlyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainly.app.trainlyapp.services.Login;
import com.trainly.app.trainlyapp.services.User;

@Controller
public class LoginController {
    private final Login loginService;

    @Autowired
    public LoginController(Login loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/Login"; // Devuelve la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = loginService.loginUser (email, password);
        if (user != null) {
            // Usuario autenticado correctamente
            return "redirect:/dashboard"; // Redirige al dashboard
        } else {
            // Credenciales inválidas
            model.addAttribute("error", "Invalid username or password");
            return "auth/Login"; // Devuelve a la página de inicio de sesión con un mensaje de error
        }
    }
}