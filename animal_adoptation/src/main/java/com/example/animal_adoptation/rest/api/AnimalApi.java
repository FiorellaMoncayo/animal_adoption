package com.example.animal_adoptation.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.animal_adoptation.application.DTO.AnimalDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Animal API", description = "API for managing animals")
@RequestMapping("/animal")
public interface AnimalApi {
	
	//read(fnd)
	@Operation(summary = "Find a animal")
	@ApiResponse(description = "Succesful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = AnimalDTO.class)))
	@ApiResponse(description = "Animal not found", responseCode = "404")
	@GetMapping("/{reiac}")
	ResponseEntity<AnimalDTO>findByReiac(
			@Parameter(description= "Username of the user to be obtained", required= true, in=ParameterIn.PATH)
			@PathVariable ("reiac") Integer reiac);
	@GetMapping("/{name}")
	ResponseEntity<AnimalDTO>findByName(
			@Parameter(description= "Username of the user to be obtained", required= true, in=ParameterIn.PATH)
			@PathVariable ("name") String name);
	//create
	@Operation(summary = "Create animal")
	@PostMapping("/new")
	ResponseEntity<AnimalDTO> createAnimal(@RequestBody AnimalDTO animalDTO);
}
