package com.trainly.app.trainlyapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/index.html", "/login", "/auth/Login.html", "/register", "/auth/Register.html", "/user/TrainningPlanView.html").permitAll() // Permitir acceso público a estas rutas
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") // Especificar la página de inicio de sesión
                .permitAll() // Permitir a todos los usuarios acceder a la página de inicio de sesión
            )
            .logout(logout -> logout
                .permitAll() // Permitir a todos los usuarios cerrar sesión
            );

        return http.build();
    }

     @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}