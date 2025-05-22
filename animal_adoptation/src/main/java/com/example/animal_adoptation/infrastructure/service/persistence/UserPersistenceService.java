package com.example.animal_adoptation.infrastructure.service.persistence;

import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.port.UserRepositoryPort;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserPersistenceService implements UserRepositoryPort {
    public static final Logger logger = LoggerFactory.getLogger(UserPersistenceService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserPersistenceService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> authenticate(String username, String rawPassword) {
        return findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }

    @Override
    public User save(User domainUser) {
        try {
            UserBBD entity = convertToEntity(domainUser);
            entity.setPassword(passwordEncoder.encode(domainUser.getPassword()));
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
            if (user.getId() == null) {
                logger.warn("User ID is null for update");
                return Optional.empty();
            }
            Optional<UserBBD> existingUser = userRepository.findById(user.getId());
            if (existingUser.isEmpty()) {
                logger.warn("User not found for ID: {}", user.getId());
                return Optional.empty();
            }

            // Check if the new username is taken by another user
            if (!existingUser.get().getUsername().equals(user.getUsername()) &&
                    userRepository.findByUsername(user.getUsername()).isPresent()) {
                logger.warn("Username already exists: {}", user.getUsername());
                return Optional.empty();
            }

            int rowsAffected = userRepository.updateUser(
                    user.getId(),
                    user.getUsername(),
                    passwordEncoder.encode(user.getPassword())
            );
            if (rowsAffected == 0) {
                logger.warn("No rows affected for user update: {}", user.getUsername());
                return Optional.empty();
            }

            return userRepository.findById(user.getId())
                    .map(this::convertToDomain);
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation updating user: {}", e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<User> deleteUser(Integer id) {
        try {
            Optional<UserBBD> user = userRepository.findById(id);
            if (user.isPresent()) {
                userRepository.deleteById(id);
                return Optional.of(convertToDomain(user.get()));
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error deleting user {}: {}", id, e.getMessage());
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