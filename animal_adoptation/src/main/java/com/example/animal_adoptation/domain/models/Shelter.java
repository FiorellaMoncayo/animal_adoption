package com.example.animal_adoptation.domain.models;

import com.example.animal_adoptation.interfaces.DTO.ShelterDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "shelter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shelter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
}
