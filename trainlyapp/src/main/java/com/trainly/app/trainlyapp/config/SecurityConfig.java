package com.trainly.app.trainlyapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/register", "/api/login") // Excluir estos endpoints de la protección CSRF
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/index.html", "/login", "/auth/Login.html", "/register", "/auth/Register.html", "/dashboard", "/perfil", "/user-view", "/training-plan-view", "/notification-view", "/payment-view").permitAll() // Permitir acceso público a estas rutas
                .requestMatchers("/api/register", "/api/login").permitAll() // Permitir acceso a los endpoints de registro y login
                .requestMatchers("/dashboard.html", "/user-view", "/training-plan-view", "/notification-view", "/payment-view").authenticated()
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") // Especificar la página de inicio de sesión
                .defaultSuccessUrl("/dashboard.html", true) // Redirigir a dashboard.html después de iniciar sesión
                .permitAll() // Permitir acceso público a la página de inicio de sesión
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login") // Redirigir a la página de inicio de sesión tras cerrar sesión
                .permitAll() // Permitir que todos los usuarios cierren sesión
            );

        return http.build();
    }
}