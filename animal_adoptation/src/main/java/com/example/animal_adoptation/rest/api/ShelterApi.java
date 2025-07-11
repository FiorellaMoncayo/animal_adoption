package com.example.animal_adoptation.rest.api;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.application.DTO.ShelterDTO;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.Shelter;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import java.util.List;

@Tag(name = "Shelter API", description = "API for managing shelters")
@RequestMapping("/shelters")
public interface ShelterApi {
    @Operation(summary = "Get all shelters")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Shelters not found", responseCode = "404")
    @GetMapping("/list")
    ResponseEntity<List<ShelterDTO>> getAllShelters();

    @Operation(summary = "Login Shelter")
    @ApiResponse(description = "Shelter logged in", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Invalid credentials", responseCode = "401")
    @PostMapping("/login")
    ResponseEntity<ShelterDTO> login(@RequestBody ShelterDTO shelterDTO);

    @Operation(summary = "Find a shelter")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @GetMapping("/sheltername/{sheltername}")
    ResponseEntity<ShelterDTO> findBysheltername(
            @Parameter(description = "Shelter to find", required = true, in = ParameterIn.PATH)
            @PathVariable("sheltername") String sheltername);
    
    @Operation(summary = "Find a shelter id")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Id not found", responseCode = "404")
    @GetMapping("/{id}")
    ResponseEntity<ShelterDTO> findByShelterId(
            @Parameter(description = "Shelter id to find", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Integer id);

    @Operation(summary = "Create shelter")
    @ApiResponse(description = "Shelter created", responseCode = "201")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PostMapping("/create")
    ResponseEntity<ShelterDTO> createShelter(@RequestBody ShelterDTO shelterDTO);

    @Operation(summary = "Update shelter")
    @ApiResponse(description = "Shelter updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PutMapping("/{shelterId}")
    ResponseEntity<ShelterDTO> updateShelter(
            @Parameter(description = "Shelter id", required = true, in = ParameterIn.PATH)
            @PathVariable("shelterId") Integer shelterId,
            @Parameter(description = "New shelter info", required = true)
            @RequestBody ShelterDTO shelterDTO);

    @Operation(summary = "Delete shelter")
    @ApiResponse(description = "Shelter deleted", responseCode = "204")
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @DeleteMapping("/{shelterId}")
    ResponseEntity<Void> deleteShelter(
            @Parameter(description = "Shelter to delete", required = true, in = ParameterIn.PATH)
            @PathVariable("shelterId") Integer shelterId);
}