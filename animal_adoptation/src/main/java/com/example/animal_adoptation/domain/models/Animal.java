package com.example.animal_adoptation.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reiac")
	int reiac;
	@Column(name="name")
	String name;
}
