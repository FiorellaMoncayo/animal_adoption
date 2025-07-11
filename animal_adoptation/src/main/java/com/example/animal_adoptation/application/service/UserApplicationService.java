package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.service.UserDomainService;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Optional;
import static com.example.animal_adoptation.infrastructure.service.persistence.UserPersistenceService.logger;

@Service
public class UserApplicationService {

    private final UserDomainService userDomainService;

    public UserApplicationService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    public Optional<UserDTO> authenticate(String username, String password) {
        return userDomainService.authenticate(username, password)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), null));
    }

    public Optional<UserDTO> findByUserId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("UserId not found");
        }
        return userDomainService.findByUserId(id)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> findByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return userDomainService.findByUsername(username)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> createUser(UserDTO userDTO) {
        if (userDTO == null) {
            return Optional.empty();
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank() ||
                userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            return Optional.empty();
        }

        try {
            User user = convertToDomain(userDTO);
            return userDomainService.createUser(user)
                    .map(this::convertToDTO);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Sorry, try another username!: {}", userDTO.getUsername());
            return Optional.empty();
        } catch (RuntimeException e) {
            logger.error("Error creating user", e);
            return Optional.empty();
        }
    }

    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == null) {
            logger.warn("Invalid user data or missing ID");
            return Optional.empty();
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()){
            logger.warn("Incomplete user data");
            return Optional.empty();
        }

        try {
            User user = convertToDomain(userDTO);
            return userDomainService.updateUser(user)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error updating user", e);
            return Optional.empty();
        }
    }

    public Optional<UserDTO> deleteUser(Integer id) {
        if (id == null) {
            logger.warn("Invalid ID");
            return Optional.empty();
        }

        try {
            return userDomainService.deleteUser(id)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error deleting user with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }

    private User convertToDomain(UserDTO userDTO) {
        return new User(
                userDTO.getId(), // Include ID
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