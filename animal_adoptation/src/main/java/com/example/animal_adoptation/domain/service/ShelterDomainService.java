package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.models.User;


import java.util.List;
import java.util.Optional;

public interface ShelterDomainService {
    Optional<Shelter> authenticate(String sheltername, String rawPassword);
    Optional<Shelter> findByShelterId (Integer id);
    Optional<Shelter> findBysheltername (String sheltername);
    Optional<Shelter> createShelter(Shelter shelter);
    Optional<Shelter> updateShelter(String sheltername, Shelter shelter);
    Optional<Shelter> deleteShelter(Integer shelterId);
    Optional<List<Shelter>> getAllShelters();
}
