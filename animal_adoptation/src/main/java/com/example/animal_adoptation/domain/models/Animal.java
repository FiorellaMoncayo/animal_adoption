package com.example.animal_adoptation.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name= "animal")
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reiac")
	int reiac;
	@Column(name="name")
	String name;
}
