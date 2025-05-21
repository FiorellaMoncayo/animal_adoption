package com.example.animal_adoptation.domain.port;

import java.util.Optional;

import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.models.User;

public interface ShelterRepositoryPort {
	Optional<Shelter> findByShelterId (Integer id);
	Optional<Shelter> findBysheltername (String sheltername);
	Shelter save (Shelter shelter);
	Optional<Shelter> createShelter(Shelter shelter);
	Optional<Shelter> updateShelter(Shelter shelter);
	Optional<Shelter> deleteShelter(Integer shelterId);
	Optional<Shelter> authenticate(String sheltername, String password);
}