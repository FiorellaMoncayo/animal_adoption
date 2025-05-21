package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.models.User;
import com.example.animal_adoptation.domain.service.ShelterDomainService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.infrastructure.service.persistence.ShelterPersistenceService;

import java.util.Optional;


@Service
public class ShelterDomainServiceImpl implements ShelterDomainService {

    private final ShelterPersistenceService shelterPersistenceService;

    public ShelterDomainServiceImpl(ShelterPersistenceService shelterPersistenceService) {
        this.shelterPersistenceService = shelterPersistenceService;
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
    public Optional<Shelter> updateShelter(Shelter shelter) {
        if (shelter.getSheltername() == null || shelter.getSheltername().isBlank() ||
                shelter.getPassword() == null || shelter.getPassword().isBlank()) {
            throw new IllegalArgumentException("Shelter data incomplete");
        }
        if (shelter.getId() == null) {
            throw new IllegalArgumentException("Shelter ID cannot be null");
        }
        return shelterPersistenceService.updateShelter(shelter);
    }

    @Override
    public Optional<Shelter> deleteShelter(Integer shelterId) {
        if (shelterId == null) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        return shelterPersistenceService.deleteShelter(shelterId);
    }


}