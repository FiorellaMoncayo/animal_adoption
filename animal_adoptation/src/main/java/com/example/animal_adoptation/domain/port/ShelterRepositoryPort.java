package com.example.animal_adoptation.domain.port;

import java.util.Optional;

import com.example.animal_adoptation.domain.models.Shelter;

public interface ShelterRepositoryPort {
	Optional<Shelter> findBySheltername (String sheltername);
}