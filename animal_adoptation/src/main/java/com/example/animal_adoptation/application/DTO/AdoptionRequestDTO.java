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
    private LocalDateTime requestDate;

    // Constructor para convertir de modelo de dominio a DTO
    public AdoptionRequestDTO(AdoptionRequest request) {
        if (request != null) {
            this.id = request.getId();
            this.userId = request.getUserId();
            this.animalId = request.getAnimalId();
            this.shelterId = request.getShelterId();
            this.status = request.getStatus() != null ? request.getStatus().name() : null;
            this.requestDate = request.getRequestDate();
        }
    }

    // Metodo para convertir de DTO a modelo de dominio
    public AdoptionRequest toModel() {
        return AdoptionRequest.builder()
                .id(this.id)
                .userId(this.userId)
                .animalId(this.animalId)
                .shelterId(this.shelterId)
                .status(this.status != null ? AdoptionRequest.AdoptionStatus.valueOf(this.status) : null)
                .requestDate(this.requestDate)
                .build();
    }
}