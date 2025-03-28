package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.service.UserApplicationService;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.rest.api.UserApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {

    private final UserApplicationService userService;

    public UserController(UserApplicationService userService) {
        this.userService = userService;
    }
//    @PostMapping("/user")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
//        UserDTO newUser = userService.createUser(userDTO);
//        return ResponseEntity.ok(newUser);
//    }
    public ResponseEntity<UserDTO> findByUsername (String username) {
        return userService.findByUsername(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
