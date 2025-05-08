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
    public Optional<User> findByUserId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return userRepository.findById(id)
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
            Optional<UserBBD> existingUserOpt = userRepository.findById(user.getId());
            if (existingUserOpt.isEmpty()) {
                logger.warn("User not found for id: {}", user.getId());
                return Optional.empty();
            }
            boolean hasNewUsername = user.getUsername() != null && !user.getUsername().isBlank();
            boolean hasNewPassword = user.getPassword() != null && !user.getPassword().isBlank();
            if (!hasNewUsername && !hasNewPassword) {
                logger.warn("No valid data was provided for update.");
                return Optional.empty();
            }

            UserBBD existingUser = existingUserOpt.get();
            if (hasNewUsername) {
                existingUser.setUsername(user.getUsername());
            }
            if (hasNewPassword) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                existingUser.setPassword(encodedPassword);
            }
            userRepository.save(existingUser);
            return Optional.of(convertToDomain(existingUser));
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