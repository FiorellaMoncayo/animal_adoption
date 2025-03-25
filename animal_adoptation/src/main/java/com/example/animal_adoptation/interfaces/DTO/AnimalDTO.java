package com.example.animal_adoptation.interfaces.DTO;

import java.util.Optional;

import com.example.animal_adoptation.domain.models.Animal;
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
	@JsonProperty("reaic")
	private Integer reiac;
	
	@JsonProperty("name")
	private String name;
	
	public AnimalDTO(Animal animal) {
		Optional.ofNullable(animal).ifPresent(a -> {
			this.reiac = a.getReiac();
			this.name = a.getName();
		});
	}
	
	public Animal toModel() {
		return new Animal(reiac, name);
	}

}
