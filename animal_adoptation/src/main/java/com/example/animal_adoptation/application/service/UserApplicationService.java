package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.service.UserPersistenceService;
import com.example.animal_adoptation.interfaces.DTO.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserPersistenceService userPersistenceService;

    public UserApplicationService(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    public Optional<UserDTO> findById(Integer id) {
        return userPersistenceService.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        // Convertir UserDTO a UserBBD antes de guardar en la base de datos
        UserBBD userBBD = new UserBBD(null, userDTO.getUsername(), userDTO.getPassword());

        // Guardar en la base de datos
        UserBBD savedUser = userPersistenceService.save(userBBD);

        // Convertir de nuevo a UserDTO antes de devolverlo
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
    }
}
