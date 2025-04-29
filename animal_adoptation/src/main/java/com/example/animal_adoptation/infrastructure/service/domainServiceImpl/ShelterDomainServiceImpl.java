package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.service.ShelterDomainService;

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
        return shelterPersistenceService.updateShelter(shelter);
    }

    @Override
    public Optional<Shelter> deleteShelter(String sheltername) {
        if (sheltername == null || sheltername.isBlank()) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        return shelterPersistenceService.deleteShelter(sheltername);
    }


}