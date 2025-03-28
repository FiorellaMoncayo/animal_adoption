package com.example.animal_adoptation.application.usecases;

import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.repositories.UserRepository;

import java.util.Optional;

public class UserUseCase {
    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    public Optional<UserDTO> findByUsername(String username){
        return userRepository.findByUsername(username)
                .map(user-> new UserDTO(user.getId(),user.getUsername(),user.getPassword()));
    }
}
