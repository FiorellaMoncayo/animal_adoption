package com.example.animal_adoptation.application.DTO;

import com.example.animal_adoptation.domain.models.AdoptionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionRequestDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("animalId")
    private Integer animalId;

    @JsonProperty("shelterId")
    private Integer shelterId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("requestDate")
    private LocalDateTime requestDate; // Para que la fecha se envíe y reciba

    // Constructor para convertir de modelo de dominio a DTO
    public AdoptionRequestDTO(AdoptionRequest request) {
        if (request != null) {
            this.id = request.getId();
            this.userId = request.getUserId();
            this.animalId = request.getAnimalId();
            this.shelterId = request.getShelterId();
            this.status = request.getStatus();
            this.requestDate = request.getRequestDate();
        }
    }

    // Metodo para convertir de DTO a modelo de dominio
    public AdoptionRequest toModel() {
        return new AdoptionRequest(id, userId, animalId, shelterId, status, requestDate);
    }
}