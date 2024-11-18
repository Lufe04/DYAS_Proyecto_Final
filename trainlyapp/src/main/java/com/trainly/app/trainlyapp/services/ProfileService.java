package com.trainly.app.trainlyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.ProfileDAO;

@Service
public class ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    public CreateProfile getCompleteProfileByEmail(String email) {
        // Obtener perfil básico del usuario
        CreateProfile profile = profileDAO.getProfileByUserEmail(email);

        if (profile != null) {
            // Obtener planes de entrenamiento
            List<TrainningPlan> trainingPlans = profileDAO.getTrainingPlansForUser(email);
            profile.setTrainingPlans(trainingPlans);

            // Obtener progreso de entrenamiento
            List<TrainningTracking> trainingTrackers = profileDAO.getTrainingProgressForUser(email);
            profile.setTrainingTrackers(trainingTrackers);
        }

        return profile;
    }
}
