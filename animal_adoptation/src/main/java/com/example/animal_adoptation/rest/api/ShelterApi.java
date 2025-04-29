package com.example.animal_adoptation.rest.api;


import com.example.animal_adoptation.application.DTO.ShelterDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Tag(name = "Shelter API", description = "API for managing shelters")
@RequestMapping("/shelters")
public interface ShelterApi {

    @Operation(summary = "Find a shelter")
    @ApiResponse(description = "Successful operation", responseCode = "200", content = @Content(schema = @Schema(implementation = ShelterDTO.class)))
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @GetMapping("/sheltername/{sheltername}")
    ResponseEntity<ShelterDTO> findBysheltername(
            @Parameter(description = "Shelter to find", required = true, in = ParameterIn.PATH)
            @PathVariable("sheltername") String sheltername);


    @Operation(summary = "Create shelter")
    @ApiResponse(description = "Shelter created", responseCode = "201")
    @ApiResponse(description = "Invalid input", responseCode = "400")
    @PostMapping("/create")
    ResponseEntity<ShelterDTO> createShelter(@RequestBody ShelterDTO shelterDTO);

    @Operation(summary = "Update shelter password")
    @ApiResponse(description = "Shelter updated", responseCode = "200")
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @PutMapping("/{sheltername}")
    ResponseEntity<ShelterDTO> updateShelter(
            @Parameter(description = "Shelter to update Password", required = true, in = ParameterIn.PATH)
            @PathVariable("sheltername") String sheltername,
            @RequestBody ShelterDTO shelterDTO);

    @Operation(summary = "Delete shelter")
    @ApiResponse(description = "Shelter deleted", responseCode = "204")
    @ApiResponse(description = "Shelter not found", responseCode = "404")
    @DeleteMapping("/{sheltername}")
    ResponseEntity<Void> deleteShelter(
            @Parameter(description = "Shelter to delete", required = true, in = ParameterIn.PATH)
            @PathVariable("sheltername") String sheltername);
}

