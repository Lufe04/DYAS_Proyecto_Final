package com.trainly.app.trainlyapp.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trainly.app.trainlyapp.DAO.UserDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Asegúrate de que el nombre del método sea correcto
        User user = userDAO.getUserByEmail(username); // Ajusta este método según tu implementación
        if (user == null) {
            throw new UsernameNotFoundException("User  not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>()
        );
    }

    // Método para registrar un nuevo usuario
    public boolean registerUser (User user) {
        if (!userDAO.isUserExist(user.getEmail())) {
            return userDAO.saveUser (user);
        }
        return false; // El usuario ya existe
    }
}