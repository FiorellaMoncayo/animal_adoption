package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.service.UserApplicationService;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.rest.api.UserApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {

    private final UserApplicationService userService;

    public UserController(UserApplicationService userService) {
        this.userService = userService;
    }

    //read (find) user
    public ResponseEntity<UserDTO> findByUsername(String username) {
        return userService.findByUsername(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //create
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() ||
                userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return userService.createUser(userDTO)
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(createdUser))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


}
