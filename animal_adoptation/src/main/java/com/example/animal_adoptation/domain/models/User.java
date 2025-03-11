package com.example.animal_adoptation.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name= "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name="name")
	String name;
	@Column(name="password")
	String password;
}
