package com.example.animal_adoptation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionRequest {
    private Integer id;
    private Integer userId;
    private Integer animalId;
    private Integer shelterId;
    private String status;
    private LocalDateTime requestDate;

    // Constructor para cuando se crea una nueva solicitud (sin ID y con estado inicial)
    public AdoptionRequest(Integer userId, Integer animalId, Integer shelterId) {
        this.userId = userId;
        this.animalId = animalId;
        this.shelterId = shelterId;
        this.status = "PENDING";
        this.requestDate = LocalDateTime.now();
    }
}