package com.trainly.app.trainlyapp.controllers;

<<<<<<< Updated upstream
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainly.app.trainlyapp.services.ILoginService;
import com.trainly.app.trainlyapp.services.LoginProxy;
>>>>>>> Stashed changes
import com.trainly.app.trainlyapp.services.Login;
import com.trainly.app.trainlyapp.services.User;
import com.trainly.app.trainlyapp.config.DatabaseConfig;

import java.sql.Connection;

public class LoginController {
<<<<<<< Updated upstream
    private final Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    public String login(String email, String password) {
        User user = login.loginUser(email, password);
        if (user != null) {
            // Usuario autenticado correctamente
            return "redirect:/dashboard";
        } else {
            // Credenciales inválidas
            return "Invalid username or password";
        }
    }
    
}
=======
    private final ILoginService loginService;

    @Autowired
    public LoginController() throws Exception {
        // Configuramos la conexión y el proxy para el servicio de autenticación
        Connection connection = DatabaseConfig.getConnection();
        Login realService = new Login(connection);
        this.loginService = new LoginProxy(realService);
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/Login"; // Devuelve la vista de inicio de sesión
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = loginService.login(email, password);

        if (user != null) {
            // Usuario autenticado correctamente
            model.addAttribute("username", user.getEmail());
            return "redirect:/dashboard";
        } else {
            // Credenciales inválidas
            model.addAttribute("error", "Invalid username or password");
            return "auth/Login";
        }
    }
}

>>>>>>> Stashed changes
