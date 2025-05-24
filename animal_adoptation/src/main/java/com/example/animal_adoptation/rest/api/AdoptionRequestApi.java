package com.example.animal_adoptation.rest.api;

import com.example.animal_adoptation.application.DTO.AdoptionRequestDTO;
import com.example.animal_adoptation.application.DTO.CreateAdoptionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Adoption Request API", description = "API para gestionar solicitudes de adopción")
@RequestMapping("/adoption_requests")
public interface AdoptionRequestApi {

    @Operation(summary = "Crear una nueva solicitud de adopción",
            description = "Permite a un usuario solicitar la adopción de un animal.")
    @ApiResponse(description = "Solicitud de adopción creada exitosamente", responseCode = "201",
            content = @Content(schema = @Schema(implementation = AdoptionRequestDTO.class)))
    @ApiResponse(description = "Datos inválidos (ej. IDs nulos, solicitud duplicada)", responseCode = "400")
    @PostMapping
    ResponseEntity<AdoptionRequestDTO> createAdoptionRequest(@RequestBody CreateAdoptionRequestDTO createRequestDTO);

    @Operation(summary = "Obtener todas las solicitudes de adopción para un refugio",
            description = "Permite a un refugio ver las solicitudes pendientes y gestionarlas.")
    @ApiResponse(description = "Lista de solicitudes de adopción del refugio", responseCode = "200",
            content = @Content(schema = @Schema(implementation = AdoptionRequestDTO.class)))
    @ApiResponse(description = "Refugio no encontrado o sin solicitudes", responseCode = "404")
    @GetMapping("/shelters/{shelterId}")
    ResponseEntity<List<AdoptionRequestDTO>> getShelterAdoptionRequests(
            @Parameter(description = "ID del refugio", required = true, in = ParameterIn.PATH)
            @PathVariable("shelterId") Integer shelterId);

    @Operation(summary = "Obtener todas las solicitudes de adopción de un usuario",
            description = "Permite a un usuario ver el estado de sus solicitudes.")
    @ApiResponse(description = "Lista de solicitudes de adopción del usuario", responseCode = "200",
            content = @Content(schema = @Schema(implementation = AdoptionRequestDTO.class)))
    @ApiResponse(description = "Usuario no encontrado o sin solicitudes", responseCode = "404")
    @GetMapping("/users/{userId}")
    ResponseEntity<List<AdoptionRequestDTO>> getUserAdoptionRequests(
            @Parameter(description = "ID del usuario", required = true, in = ParameterIn.PATH)
            @PathVariable("userId") Integer userId);

    @Operation(summary = "Actualizar el estado de una solicitud de adopción",
            description = "Permite a un refugio aceptar o rechazar una solicitud.")
    @ApiResponse(description = "Estado de la solicitud actualizado", responseCode = "200",
            content = @Content(schema = @Schema(implementation = AdoptionRequestDTO.class)))
    @ApiResponse(description = "Solicitud no encontrada o estado inválido", responseCode = "404")
    @PutMapping("/{requestId}")
    ResponseEntity<AdoptionRequestDTO> updateAdoptionRequestStatus(
            @Parameter(description = "ID de la solicitud de adopción", required = true, in = ParameterIn.PATH)
            @PathVariable("requestId") Integer requestId,
            @Parameter(description = "Nuevo estado (PENDING, ACCEPTED, REJECTED)", required = true)
            @RequestParam("status") String newStatus); // Usamos @RequestParam para el status para simplificar

    @Operation(summary = "Eliminar una solicitud de adopción",
            description = "Elimina una solicitud de adopción por su ID.")
    @ApiResponse(description = "Solicitud eliminada exitosamente", responseCode = "204")
    @ApiResponse(description = "Solicitud no encontrada", responseCode = "404")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAdoptionRequest(
            @Parameter(description = "ID de la solicitud de adopción a eliminar", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Integer id);
}