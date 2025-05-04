package com.example.animal_adoptation.application.DTO;

import com.example.animal_adoptation.domain.models.Shelter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("sheltername")
    private String sheltername;

    @JsonProperty("password")
    private String password;

    // Constructor que convierte de Shelter a ShelterDTO
    public ShelterDTO(Shelter shelter) {
        if (shelter != null) {
            this.id = shelter.getId();
            this.sheltername = shelter.getSheltername();
            this.password = shelter.getPassword();
        }
    }

    // Metodo para convertir de UserDTO a User
    public Shelter toModel() {
        return new Shelter(id, sheltername, password);
    }
}

