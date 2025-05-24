package com.example.animal_adoptation.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.models.User;

public interface ShelterRepositoryPort {
	Optional<Shelter> findByShelterId (Integer id);
	Optional<Shelter> findBysheltername (String sheltername);
    Optional<List<Shelter>> getAllShelters();
    Shelter save (Shelter shelter);
	Optional<Shelter> createShelter(Shelter shelter);
	Optional<Shelter> updateShelter(Integer shelterId, Shelter shelter);
	Optional<Shelter> deleteShelter(Integer shelterId);
	Optional<Shelter> authenticate(String sheltername, String password);
}