package com.example.animal_adoptation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shelter {
	private Integer id;
	private String sheltername;
	private String password;
	private String email;
	private String phone;
}