package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.User;

import java.util.Optional;

public interface UserRepositoryPort {
    //Optional<User> findById(Integer id);
    Optional<User> findByUsername (String username);
    Optional<User> createUser(UserDTO userDTO);
}


