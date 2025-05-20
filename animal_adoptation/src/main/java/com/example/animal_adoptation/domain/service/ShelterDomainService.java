package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.models.User;


import java.util.Optional;

public interface ShelterDomainService {
    Optional<Shelter> authenticate(String sheltername, String rawPassword);
    Optional<Shelter> findBysheltername (String sheltername);
    Optional<Shelter> createShelter(Shelter shelter);
    Optional<Shelter> updateShelter(Shelter shelter);
    Optional<Shelter> deleteShelter(Integer shelterId);

}
