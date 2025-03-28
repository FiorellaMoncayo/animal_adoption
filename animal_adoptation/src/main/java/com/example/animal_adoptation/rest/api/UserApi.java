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

	//read(fnd)
	@Operation(summary = "Find a user")
	@ApiResponse(description = "Succesful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	@ApiResponse(description = "User not found", responseCode = "404")
	@GetMapping("/username/{username}")
	ResponseEntity<UserDTO>findByUsername(
			@Parameter(description= "Username of the user to be obtained", required= true, in=ParameterIn.PATH)
			@PathVariable ("username") String username);
	//create
	@Operation(summary = "Create user")
	@PostMapping("/create")
	ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO);


	
}
