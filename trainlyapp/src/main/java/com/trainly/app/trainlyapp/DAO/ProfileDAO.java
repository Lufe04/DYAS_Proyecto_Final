package com.trainly.app.trainlyapp.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.trainly.app.trainlyapp.services.CreateProfile;
import com.trainly.app.trainlyapp.services.TrainningPlan;
import com.trainly.app.trainlyapp.services.TrainningTracking;

@Repository
public class ProfileDAO {

    private Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TrainningPlan> getTrainingPlansForUser(String email) {
        int userId = getUserIdByEmail(email);
        List<TrainningPlan> trainingPlans = new ArrayList<>();
        
        if (userId != -1) {
            String query = "SELECT id, plan_name, start_date, end_date FROM training_plans WHERE user_id = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    int planId = resultSet.getInt("id");
                    String planName = resultSet.getString("plan_name");
                    Date startDate = resultSet.getDate("start_date");
                    Date endDate = resultSet.getDate("end_date");
                    
                    trainingPlans.add(new TrainningPlan(planId, planName, startDate, endDate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return trainingPlans;
    }

    public List<TrainningTracking> getTrainingProgressForUser(String email) {
        int userId = getUserIdByEmail(email);
        List<TrainningTracking> trainingTrackers = new ArrayList<>();
        
        if (userId != -1) {
            String query = "SELECT id, training_plan_id, progress, date FROM training_tracker WHERE user_id = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    int trackerId = resultSet.getInt("id");
                    int trainingPlanId = resultSet.getInt("training_plan_id");
                    String progress = resultSet.getString("progress");
                    Timestamp date = resultSet.getTimestamp("date");
                    
                    trainingTrackers.add(new TrainningTracking(trackerId, trainingPlanId, progress, date));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return trainingTrackers;
    }

    public int getUserIdByEmail(String email) {
        String query = "SELECT id FROM users WHERE email = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; // Usuario no encontrado
    }

    public CreateProfile getProfileByUserEmail(String email) {
        String query = "SELECT username, email, user_type FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String userType = resultSet.getString("user_type");
                return new CreateProfile(username, email, userType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Usuario no encontrado
    }
    
}


