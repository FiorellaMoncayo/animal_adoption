package com.example.animal_adoptation.application.DTO;

import java.util.Optional;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AnimalDTO {
	
	@JsonProperty("id")
    private Integer id;
	
	@JsonProperty("reaic")
	private int reiac;

	@JsonProperty("name")
	private String name;

	@JsonProperty("shelterId")
    private Integer shelterId;
	
	// Constructor que convierte de Animal a AnimalDTO
	public AnimalDTO(Animal animal) {
		Optional.ofNullable(animal).ifPresent(a -> {
		this.id = a.getId();
			this.reiac = a.getReiac();
			this.name = a.getName();
			this.shelterId = Optional.ofNullable(a.getShelter()).map(Shelter::getId).orElse(null);
		});
	}

	// Método para convertir de AnimalDTO a Animal
	public Animal toModel() {
		return new Animal(id, reiac, name, null);
	}

}
