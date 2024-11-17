package com.trainly.app.trainlyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainly.app.trainlyapp.services.CreateProfile;
import com.trainly.app.trainlyapp.services.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/current")
    public CreateProfile getCurrentUserProfile(Authentication authentication) {
        String email = authentication.getName(); // Email del usuario autenticado
        return profileService.getCompleteProfileByEmail(email); // Obtener perfil completo
    }
}


