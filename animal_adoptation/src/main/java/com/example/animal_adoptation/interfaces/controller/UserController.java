package com.example.animal_adoptation.interfaces.controller;

import com.example.animal_adoptation.application.service.UserApplicationService;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.interfaces.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserApplicationService userService;

    public UserController(UserApplicationService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return ResponseEntity.ok(newUser);
    }

}
