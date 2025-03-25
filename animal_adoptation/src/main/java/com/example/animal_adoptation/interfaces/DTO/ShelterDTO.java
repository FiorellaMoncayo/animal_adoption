package com.example.animal_adoptation.interfaces.DTO;


import java.util.Optional;

import com.example.animal_adoptation.domain.models.Shelter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ShelterDTO {
	@JsonProperty ("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("password")
	private String password;
	
	public ShelterDTO(Shelter shelter) {
		Optional.ofNullable(shelter).ifPresent(s -> {
            this.id = s.getId();
            this.name = s.getName();
            this.password = s.getPassword();
		});
	}
	
	public Shelter toModel() {
		return new Shelter(id, name, password);
	}

}

