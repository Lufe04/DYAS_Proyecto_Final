package com.trainly.app.trainlyapp.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainly.app.trainlyapp.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}