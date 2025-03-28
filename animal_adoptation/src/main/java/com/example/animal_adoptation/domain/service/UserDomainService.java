package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;

import java.util.Optional;

public interface UserDomainService {

    Optional<User> findByUsername (String username);

}
