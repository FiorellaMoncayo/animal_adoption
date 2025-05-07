package com.example.animal_adoptation.rest.api;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.animal_adoptation.application.DTO.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Tag(name = "User API", description = "API for managing users")
@RequestMapping("/users")

public interface UserApi {
	@Operation(summary = "Login user")
	@ApiResponse(description = "User logged in", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	@ApiResponse(description = "Invalid credentials", responseCode = "401")
	@PostMapping("/login")
	ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO);

	@Operation(summary = "Find a user")
	@ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	@ApiResponse(description = "User not found", responseCode = "404")
	@GetMapping("/username/{username}")
	ResponseEntity<UserDTO> findByUsername(
			@Parameter(description = "Username to find", required = true, in = ParameterIn.PATH)
			@PathVariable("username") String username);

	@Operation(summary = "Find a user id")
	@ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	@ApiResponse(description = "Id not found", responseCode = "404")
	@GetMapping("/username/{id}")
	ResponseEntity<UserDTO> findByUserId(
			@Parameter(description = "User id to find", required = true, in = ParameterIn.PATH)
			@PathVariable("user id") Integer id);

	@Operation(summary = "Create user")
	@ApiResponse(description = "User created", responseCode = "201")
	@ApiResponse(description = "Invalid input", responseCode = "400")
	@PostMapping("/create")
	ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO);

	@Operation(summary = "Update user password")
	@ApiResponse(description = "User updated", responseCode = "200")
	@ApiResponse(description = "User not found", responseCode = "404")
	@PutMapping("/{username}")
	ResponseEntity<UserDTO> updateUser(
			@Parameter(description = "Username to update Password", required = true, in = ParameterIn.PATH)
			@PathVariable("username") String username,
			@RequestBody UserDTO userDTO);

	@Operation(summary = "Delete user")
	@ApiResponse(description = "User deleted", responseCode = "204")
	@ApiResponse(description = "User not found", responseCode = "404")
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(
			@Parameter(description = "User id to delete", required = true, in = ParameterIn.PATH)
			@PathVariable("id") Integer id);
}