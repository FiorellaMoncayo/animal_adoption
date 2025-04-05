package com.example.animal_adoptation.infrastructure.service.persistence;

import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.port.UserRepositoryPort;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Service
public class UserPersistenceService implements UserRepositoryPort {
    public static final Logger logger = LoggerFactory.getLogger(UserPersistenceService.class);
    private final UserRepository userRepository;

    public UserPersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User domainUser) {
        try {
            UserBBD entity = convertToEntity(domainUser);
            UserBBD savedEntity = userRepository.save(entity);
            return convertToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw new IllegalArgumentException("User data violation rules", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return userRepository.findByUsername(username)
                .map(this::convertToDomain);
    }

    @Override
    public Optional<User> createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("User already exists");
        }
        try {
            User createdUser = save(user);
            return Optional.of(createdUser);
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> updateUser(User user) {
        try {
            Optional<UserBBD> existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser.isEmpty()) {
                logger.warn("User not found for username: {}", user.getUsername());
                return Optional.empty();
            }

            int rowsAffected = userRepository.updatePassword(user.getUsername(), user.getPassword());
            if (rowsAffected == 0) {
                return Optional.empty();
            }

            return userRepository.findByUsername(user.getUsername())
                    .map(this::convertToDomain);
        } catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> deleteUser(String username) {
        try {
            Optional<UserBBD> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                userRepository.deleteByUsername(username);
                return Optional.of(convertToDomain(user.get()));
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error deleting user {}: {}", username, e.getMessage());
            return Optional.empty();
        }
    }

    private UserBBD convertToEntity(User domain) {
        UserBBD entity = new UserBBD();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        return entity;
    }

    private User convertToDomain(UserBBD entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getPassword());
    }
}