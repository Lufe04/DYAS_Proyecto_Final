package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.services.User;

import java.util.HashMap;
import java.util.Map;

public class LoginProxy implements ILoginService {
    private ILoginService loginService;
    private Map<String, Integer> loginAttempts;

    public LoginProxy(ILoginService loginService) {
        this.loginService = loginService;
        this.loginAttempts = new HashMap<>();
    }

    @Override
    public User login(String email, String password) {
        if (loginAttempts.getOrDefault(email, 0) >= 3) {
            System.out.println("Cuenta bloqueada por múltiples intentos fallidos.");
            return null;
        }

        User user = loginService.login(email, password);

        if (user != null) {
            loginAttempts.remove(email);
        } else {
            loginAttempts.put(email, loginAttempts.getOrDefault(email, 0) + 1);
        }

        return user;
    }
}
