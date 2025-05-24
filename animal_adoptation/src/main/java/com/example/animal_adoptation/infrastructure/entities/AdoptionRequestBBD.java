package com.example.animal_adoptation.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdoptionRequestBBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "animal_id", nullable = false)
    private Integer animalId;

    @Column(name = "shelter_id", nullable = false)
    private Integer shelterId;

    @Column(name = "status", nullable = false)
    private String status; // "PENDING", "ACCEPTED", "REJECTED"

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    public AdoptionRequestBBD(Integer userId, Integer animalId, Integer shelterId) {
        this.userId = userId;
        this.animalId = animalId;
        this.shelterId = shelterId;
        this.status = "PENDING"; // Estado inicial por defecto
        this.requestDate = LocalDateTime.now();
    }
}