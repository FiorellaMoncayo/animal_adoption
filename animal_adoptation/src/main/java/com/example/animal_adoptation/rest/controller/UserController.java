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

    @Override
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() ||
                userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return userService.createUser(userDTO)
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(createdUser))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    //update para el password:
    @Override
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() ||
                userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (!username.equals(userDTO.getUsername())) {
            return ResponseEntity.badRequest().build();
        }

        return userService.updateUser(userDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //update para el username:

    @Override
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return userService.deleteUser(username)
                .map(userDTO -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}