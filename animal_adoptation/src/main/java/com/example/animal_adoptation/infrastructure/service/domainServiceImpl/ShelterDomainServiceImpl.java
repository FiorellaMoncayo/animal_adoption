package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

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
    public Optional<Shelter> findBysheltername(String sheltername) {
        return shelterPersistenceService.findBysheltername(sheltername);
    }

    @Override
    public Optional<Shelter> findByShelterId(Integer id) {
        return shelterPersistenceService.findByShelterId(id);
    }

    @Override
    public Optional<Shelter> createShelter(Shelter shelter) {
        return shelterPersistenceService.createShelter(shelter);
    }

    @Override
    public Optional<Shelter> updateShelter(Shelter shelter) {
        boolean isShelternameValid = shelter.getSheltername() != null && !shelter.getSheltername().isBlank();
        boolean isPasswordValid = shelter.getPassword() != null && !shelter.getPassword().isBlank();

        if(!isShelternameValid && !isPasswordValid) {
            throw new IllegalArgumentException("At least one field must be provided for update");
        }

        return shelterPersistenceService.updateShelter(shelter);
    }

    @Override
    public Optional<Shelter> deleteShelter(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        return shelterPersistenceService.deleteShelter(id);
    }
}