package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.domain.models.User;

import java.util.Optional;
public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    Optional<User> createUser(User user);
    User save(User user);
    Optional<User> updateUser(User user);
    Optional<User> deleteUser(String username);
}