package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.domain.models.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername (String username);
}


