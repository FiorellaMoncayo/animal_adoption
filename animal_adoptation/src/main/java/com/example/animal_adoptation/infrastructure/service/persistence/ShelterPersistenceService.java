
package com.example.animal_adoptation.infrastructure.service.persistence;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.port.ShelterRepositoryPort;
import com.example.animal_adoptation.infrastructure.entities.ShelterBBD;
import com.example.animal_adoptation.infrastructure.repositories.ShelterRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Service
public class ShelterPersistenceService implements ShelterRepositoryPort {
    public static final Logger logger = LoggerFactory.getLogger(ShelterPersistenceService.class);
    private final ShelterRepository shelterRepository;
    private final PasswordEncoder passwordEncoder;

    public ShelterPersistenceService(ShelterRepository shelterRepository, PasswordEncoder passwordEncoder) {
        this.shelterRepository = shelterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Shelter> authenticate(String sheltername, String rawPassword) {
        return findBysheltername(sheltername)
                .filter(shelter -> passwordEncoder.matches(rawPassword, shelter.getPassword()));
    }

    @Override
    public Shelter save(Shelter domainShelter) {
        try {
            ShelterBBD entity = convertToEntity(domainShelter);
            entity.setPassword(passwordEncoder.encode(domainShelter.getPassword()));
            ShelterBBD savedEntity = shelterRepository.save(entity);
            return convertToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving shelter: {}", e.getMessage());
            throw new IllegalArgumentException("Shelter data violation rules", e);
        }
    }

    @Override
    public Optional<Shelter> findByShelterId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return shelterRepository.findById(id)
                .map(this::convertToDomain);
    }
    
    @Override
    public Optional<Shelter> findBysheltername(String sheltername) {
        if (sheltername == null || sheltername.isBlank()) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        return shelterRepository.findBysheltername(sheltername)
                .map(this::convertToDomain);
    }

    @Override
    public Optional<Shelter> createShelter(Shelter shelter) {
        if (shelterRepository.findBysheltername(shelter.getSheltername()).isPresent()) {
            throw new DataIntegrityViolationException("Shelter already exists");
        }
        try {
            Shelter createdShelter = save(shelter);
            return Optional.of(createdShelter);
        } catch (Exception e) {
            logger.error("Error creating shelter: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Shelter> updateShelter(Shelter shelter) {
        try {
            if (shelter.getId() == null) {
                logger.warn("Shelter ID is null for update");
                return Optional.empty();
            }
            Optional<ShelterBBD> existingShelter = shelterRepository.findById(shelter.getId());
            if (existingShelter.isEmpty()) {
                logger.warn("Shelter not found for ID: {}", shelter.getId());
                return Optional.empty();
            }

            // Check if the new sheltername is taken by another shelter
            if (!existingShelter.get().getSheltername().equals(shelter.getSheltername()) &&
                    shelterRepository.findBysheltername(shelter.getSheltername()).isPresent()) {
                logger.warn("Sheltername already exists: {}", shelter.getSheltername());
                return Optional.empty();
            }

            int rowsAffected = shelterRepository.updateShelter(
                    shelter.getId(),
                    shelter.getSheltername(),
                    passwordEncoder.encode(shelter.getPassword())
            );
            if (rowsAffected == 0) {
                logger.warn("No rows affected for shelter update: {}", shelter.getSheltername());
                return Optional.empty();
            }

            return shelterRepository.findById(shelter.getId())
                    .map(this::convertToDomain);
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation updating shelter: {}", e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error updating shelter: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Shelter> deleteShelter(Integer shelterId) {
        try {
            Optional<ShelterBBD> shelter = shelterRepository.findById(shelterId);
            if (shelter.isPresent()) {
                shelterRepository.deleteById(shelterId);
                return Optional.of(convertToDomain(shelter.get()));
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error deleting shelter {}: {}", shelterId, e.getMessage());
            return Optional.empty();
        }
    }



    private ShelterBBD convertToEntity(Shelter domain) {
        ShelterBBD entity = new ShelterBBD();
        //entity.setId(domain.getId());
        entity.setSheltername(domain.getSheltername());
        entity.setPassword(domain.getPassword());
        return entity;
    }

    private Shelter convertToDomain(ShelterBBD entity) {
        return new Shelter(entity.getId(), entity.getSheltername(), entity.getPassword());
    }
}