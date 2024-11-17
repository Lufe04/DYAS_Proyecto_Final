package com.trainly.app.trainlyapp.controllers;

import com.trainly.app.trainlyapp.services.CreateProfile;
import com.trainly.app.trainlyapp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/email/{email}")
    public CreateProfile getProfile(@PathVariable String email) {
        return profileService.getProfileByEmail(email);
    }
}
