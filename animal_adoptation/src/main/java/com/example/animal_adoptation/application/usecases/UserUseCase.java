package com.example.animal_adoptation.application.usecases;

import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.service.UserDomainService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUseCase {

    private final UserDomainService userDomainService;

    public UserUseCase(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    public Optional<UserDTO> findByUsername(String username) {
        if (username == null || username.isBlank()) {
            return Optional.empty();
        }
        return userDomainService.findByUsername(username)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> createUser(UserDTO userDTO) {
        if (!isValidUserDTO(userDTO)) {
            return Optional.empty();
        }
        try {
            User user = convertToDomain(userDTO);
            Optional<User> createdUser = userDomainService.createUser(user);

            return createdUser.map(this::convertToDTO);
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }

    private boolean isValidUserDTO(UserDTO userDTO) {
        return userDTO != null &&
                userDTO.getUsername() != null && !userDTO.getUsername().isBlank() &&
                userDTO.getPassword() != null && !userDTO.getPassword().isBlank();
    }

    private User convertToDomain(UserDTO userDTO) {
        return new User(
                null,
                userDTO.getUsername(),
                userDTO.getPassword()
        );
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}