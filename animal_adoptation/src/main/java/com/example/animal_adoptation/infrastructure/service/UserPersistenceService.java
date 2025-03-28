package com.example.animal_adoptation.infrastructure.service;

import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserPersistenceService {
    private static final Logger logger = LoggerFactory.getLogger(UserPersistenceService.class);
    private final UserRepository userRepository;


    public UserPersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //lo que mento a modo de prueba
//    public Optional<UserBBD> findById(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    public UserBBD save(UserBBD user) {
//        return userRepository.save(user);
//    }

    public Optional<UserBBD> findByUsername (String username){
        return userRepository.findByUsername(username);
    }



}


