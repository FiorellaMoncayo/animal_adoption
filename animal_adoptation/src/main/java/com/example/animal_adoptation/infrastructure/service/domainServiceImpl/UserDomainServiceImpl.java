package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.service.UserDomainService;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.service.persistence.UserPersistenceService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final UserPersistenceService userPersistenceService;

    public UserDomainServiceImpl(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    @Override
    public Optional<User> authenticate(String username, String rawPassword) {
        return userPersistenceService.findByUsername(username)
                .filter(user -> passwordMatches(rawPassword, user.getPassword()));
    }

    private boolean passwordMatches(String rawPassword, String hashedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userPersistenceService.findByUsername(username);
    }
    public Optional <User> findByUserId(Integer id) {
        return userPersistenceService.findByUserId(id);
    }

    @Override
    public Optional<User> createUser(User user) {
        return userPersistenceService.createUser(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("User data incomplete");
        }
        return userPersistenceService.updateUser(user);
    }

    @Override
    public Optional<User> deleteUser(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return userPersistenceService.deleteUser(id);
    }
}