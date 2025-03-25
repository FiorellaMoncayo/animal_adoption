package com.example.animal_adoptation.interfaces.DTO;


import java.util.Optional;

import com.example.animal_adoptation.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
	@JsonProperty ("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("password")
	private String password;
	
	public UserDTO(User user) {
		Optional.ofNullable(user).ifPresent(u -> {
			this.id = u.getId();
			this.name = u.getName();
			this.password = u.getPassword();
		});
	}
	
	public User toModel() {
		return new User(id, name, password);
	}

}

