package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.service.UserApplicationService;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.rest.api.UserApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {
    private final UserApplicationService userService;

    public UserController(UserApplicationService userService) {
        this.userService = userService;
    }

    @Override
    //@PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        return userService.authenticate(userDTO.getUsername(), userDTO.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public ResponseEntity<UserDTO> findByUserId(@PathVariable Integer id) {
        return userService.findByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

    @Override
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        // Find user by username to get ID
        Optional<UserDTO> existingUser = userService.findByUsername(username);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userDTO.setId(existingUser.get().getId()); // Set ID in DTO
        return userService.updateUser(userDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<UserDTO> result = userService.deleteUser(id);
        return result.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
}}