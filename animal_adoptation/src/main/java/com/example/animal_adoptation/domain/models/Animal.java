package com.example.animal_adoptation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {
	
	private int id;
	private int reiac;
	private String name;
}
