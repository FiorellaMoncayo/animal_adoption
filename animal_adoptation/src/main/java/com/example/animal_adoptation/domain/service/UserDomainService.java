package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.User;
import java.util.Optional;

public interface UserDomainService {
    Optional<User> authenticate(String username, String rawPassword);
    Optional<User> findByUsername(String username);
    Optional<User> createUser(User user);
    Optional<User> updateUser(User user);
    Optional<User> deleteUser(String username);
}
