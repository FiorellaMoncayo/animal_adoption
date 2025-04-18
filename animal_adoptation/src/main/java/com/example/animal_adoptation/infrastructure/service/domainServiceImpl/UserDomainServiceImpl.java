package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.service.UserDomainService;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.service.persistence.UserPersistenceService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDomainServiceImpl implements UserDomainService {
    private final UserPersistenceService userPersistenceService;

    public UserDomainServiceImpl(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userPersistenceService.findByUsername(username);
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
    public Optional<User> deleteUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return userPersistenceService.deleteUser(username);
    }
}