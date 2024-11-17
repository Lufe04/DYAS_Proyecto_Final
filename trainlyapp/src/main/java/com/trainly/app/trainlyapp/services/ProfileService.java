package com.trainly.app.trainlyapp.services;

import com.trainly.app.trainlyapp.DAO.ProfileDAO;
import java.sql.Connection;

public class ProfileService {

    private ProfileDAO profileDAO;

    public ProfileService(Connection connection) {
        this.profileDAO = new ProfileDAO(connection);
    }

    public CreateProfile getProfileByEmail(String email) {
        return profileDAO.getProfileByUserEmail(email);
    }
}