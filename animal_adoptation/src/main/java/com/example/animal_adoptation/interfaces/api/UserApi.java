package com.example.animal_adoptation.interfaces.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.animal_adoptation.interfaces.DTO.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "User API", description = "API for managing users")
@RequestMapping("/users")
public interface UserApi {
	
	@Operation(summary = "Create a new user")
	@ApiResponse(description = "Succesful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	@ApiResponse(description = "Nurse not found", responseCode = "404")
	@PostMapping("/createUser")
	ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO);
	
}
