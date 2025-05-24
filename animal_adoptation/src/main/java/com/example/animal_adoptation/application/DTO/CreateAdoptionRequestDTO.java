package com.example.animal_adoptation.application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdoptionRequestDTO {

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("animalId")
    private Integer animalId;

    @JsonProperty("shelterId")
    private Integer shelterId;
}