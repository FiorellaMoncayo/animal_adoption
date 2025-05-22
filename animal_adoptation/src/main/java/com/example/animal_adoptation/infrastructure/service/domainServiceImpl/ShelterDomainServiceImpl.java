package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.service.ShelterDomainService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.infrastructure.service.persistence.ShelterPersistenceService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class ShelterDomainServiceImpl implements ShelterDomainService {

    private final ShelterPersistenceService shelterPersistenceService;

    public ShelterDomainServiceImpl(ShelterPersistenceService shelterPersistenceService) {
        this.shelterPersistenceService = shelterPersistenceService;
    }

    @Override
    public Optional<List<Shelter>> getAllShelters() {
        return shelterPersistenceService.getAllShelters()
                .map(shelters -> shelters.isEmpty() ? Collections.<Shelter>emptyList() : shelters);
    }

    @Override
    public Optional<Shelter> authenticate(String sheltername, String rawPassword) {
        return shelterPersistenceService.findBysheltername(sheltername)
                .filter(shelter -> passwordMatches(rawPassword, shelter.getPassword()));
    }

    private boolean passwordMatches(String rawPassword, String hashedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
    }

    @Override
    public Optional<Shelter> findByShelterId(Integer id) {
        return shelterPersistenceService.findByShelterId(id);
    }
    
    @Override
    public Optional<Shelter> findBysheltername(String sheltername) {
        return shelterPersistenceService.findBysheltername(sheltername);
    }
    @Override
    public Optional<Shelter> createShelter(Shelter shelter) {
        return shelterPersistenceService.createShelter(shelter);
    }

    @Override
    public Optional<Shelter> updateShelter(String sheltername, Shelter shelter) {
        if (sheltername == null || sheltername.isBlank()) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        if (shelter.getSheltername() == null || shelter.getSheltername().isBlank()) {
            throw new IllegalArgumentException("Shelter data incomplete");
        }
        Optional<Shelter> existingShelter = shelterPersistenceService.findByShelterId(shelter.getId());
        if (existingShelter.isEmpty()) {
            throw new IllegalArgumentException("Shelter not found for name: " + sheltername);
        }
        
        return shelterPersistenceService.updateShelter(sheltername, shelter);
    }

    @Override
    public Optional<Shelter> deleteShelter(Integer shelterId) {
        if (shelterId == null) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        return shelterPersistenceService.deleteShelter(shelterId);
    }


}