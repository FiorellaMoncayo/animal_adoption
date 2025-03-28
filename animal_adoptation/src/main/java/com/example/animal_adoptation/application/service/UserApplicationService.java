package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.service.UserPersistenceService;
import com.example.animal_adoptation.application.DTO.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserPersistenceService userPersistenceService;

    public UserApplicationService(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    public Optional<UserDTO> findByUsername (String username){
        return userPersistenceService.findByUsername(username)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
    }

    public Optional<UserDTO> createUser(UserDTO userDTO) {
        UserBBD userBBD = new UserBBD();
        userBBD.setUsername(userDTO.getUsername());
        userBBD.setPassword(userDTO.getPassword());
        UserBBD savedUser = userPersistenceService.save(userBBD);
        return Optional.of(new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword()));

    }


//    public Optional<UserDTO> findById(Integer id) {
//        return userPersistenceService.findById(id)
//                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
//    }

//    @Transactional
//    public UserDTO createUser(UserDTO userDTO) {
//        UserBBD userBBD = new UserBBD(null, userDTO.getUsername(), userDTO.getPassword());
//        UserBBD savedUser = userPersistenceService.save(userBBD);
//        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
//    }


}
