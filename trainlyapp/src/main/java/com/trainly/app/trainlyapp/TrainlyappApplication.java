package com.trainly.app.trainlyapp;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.trainly.app.trainlyapp.config.DatabaseConfig;

@SpringBootApplication(scanBasePackages = "com.trainly.app.trainlyapp")
@EntityScan(basePackages = "com.trainly.app.trainlyapp.models")
public class TrainlyappApplication {

    
	public static void main(String[] args) {
		SpringApplication.run(TrainlyappApplication.class, args);

		try (Connection connection = DatabaseConfig.getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    
	}

}
