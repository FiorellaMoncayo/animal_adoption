package com.example.animal_adoptation.rest.api;

import java.util.List;
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

    @Operation(summary = "Find an animal by REIAC")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = AnimalDTO.class)))
    @ApiResponse(description = "Animal not found", responseCode = "404")
    @GetMapping("/{reiac}")
    ResponseEntity<AnimalDTO> findByReiac(
            @Parameter(description = "REIAC of the animal to be obtained", required = true, in = ParameterIn.PATH)
            @PathVariable("reiac") int reiac);

    @Operation(summary = "Find an animal by name")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = AnimalDTO.class)))
    @ApiResponse(description = "Animal not found", responseCode = "404")
    @GetMapping("/name/{name}")
    ResponseEntity<AnimalDTO> findByName(
            @Parameter(description = "Name of the animal to be obtained", required = true, in = ParameterIn.PATH)
            @PathVariable("name") String name);

    @Operation(summary = "Find animals by shelter")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = AnimalDTO.class)))
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @GetMapping("/shelter/{shelterId}")
    ResponseEntity<List<AnimalDTO>> findByShelter(
            @Parameter(description = "ID of the shelter", required = true, in = ParameterIn.PATH)
            @PathVariable("shelterId") Integer shelterId);

    @Operation(summary = "Create an animal")
    @ApiResponse(description = "Animal created", responseCode = "201")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PostMapping("/new")
    ResponseEntity<AnimalDTO> createAnimal(@RequestBody AnimalDTO animalDTO);

    @Operation(summary = "Update an animal")
    @ApiResponse(description = "Animal updated", responseCode = "201")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PostMapping("/{reiac}/{name}")
    ResponseEntity<AnimalDTO> updateAnimal(
            @Parameter(description = "REIAC of the animal to update", required = true, in = ParameterIn.PATH)
            @PathVariable int reiac,
            @Parameter(description = "Name of the animal to update", required = true, in = ParameterIn.PATH)
            @PathVariable String name,
            @RequestBody AnimalDTO animalDTO);

    @Operation(summary = "Delete an animal")
    @ApiResponse(description = "Animal deleted", responseCode = "204")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PostMapping("/{id}")
    ResponseEntity<Void> deleteAnimal(
            @Parameter(description = "ID of the animal to delete", required = true, in = ParameterIn.PATH)
            @PathVariable Integer id);
}