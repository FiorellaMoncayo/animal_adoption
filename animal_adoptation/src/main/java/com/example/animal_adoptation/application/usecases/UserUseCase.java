package com.example.animal_adoptation.application.usecases;

import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.UserRepository;

import java.util.Optional;

public class UserUseCase {
    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
    }

    public Optional<UserDTO> createUser(UserDTO userDTO) {
        UserBBD userBBD = new UserBBD();
        userBBD.setUsername(userDTO.getUsername());
        userBBD.setPassword(userDTO.getPassword());
        UserBBD savedUser = userRepository.save(userBBD);
        return Optional.of(new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword()));

    }
}