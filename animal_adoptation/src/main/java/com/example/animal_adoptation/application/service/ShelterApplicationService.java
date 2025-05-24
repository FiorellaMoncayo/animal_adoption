package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.application.DTO.ShelterDTO;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.service.ShelterDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShelterApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ShelterApplicationService.class);

    private final ShelterDomainService shelterDomainService;

    public ShelterApplicationService(ShelterDomainService shelterDomainService) {
        this.shelterDomainService = shelterDomainService;
    }

    public List<ShelterDTO> getAllShelters() {
        return shelterDomainService.getAllShelters()
                .map(this::convertAllToDTO)
                .orElse(Collections.emptyList());
    }

    public Optional<ShelterDTO> authenticate(String sheltername, String password) {
        return shelterDomainService.authenticate(sheltername, password)
                .map(shelter -> new ShelterDTO(shelter.getId(), shelter.getSheltername(), null, shelter.getEmail(), shelter.getPhone()));
    }

    public Optional<ShelterDTO> findByShelterId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return shelterDomainService.findByShelterId(id)
                .map(this::convertToDTO);
    }
    
    public Optional<ShelterDTO> findBysheltername(String sheltername) {
        if (sheltername == null || sheltername.isBlank()) {
            throw new IllegalArgumentException("Sheltername cannot be empty");
        }
        return shelterDomainService.findBysheltername(sheltername)
                .map(this::convertToDTO);
    }

    public Optional<ShelterDTO> createShelter(ShelterDTO shelterDTO) {
        if (shelterDTO == null || isInvalidShelterData(shelterDTO)) {
            return Optional.empty();
        }

        try {
            Shelter shelter = convertToDomain(shelterDTO);
            return shelterDomainService.createShelter(shelter).map(this::convertToDTO);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Sheltername already exists: {}", shelterDTO.getSheltername());
            return Optional.empty();
        } catch (RuntimeException e) {
            logger.error("Error creating shelter", e);
            return Optional.empty();
        }
    }

    public Optional<ShelterDTO> updateShelter(Integer shelterId, ShelterDTO shelterDTO) {
        if (shelterDTO.getSheltername() == null || shelterDTO.getSheltername().isBlank()) {
            logger.warn("Invalid shelter data or missing sheltername");
            return Optional.empty();
        }

        try {
            Optional<Shelter> existingShelter = shelterDomainService.findByShelterId(shelterId);
            if (existingShelter.isEmpty()) {
                logger.warn("Shelter not found for name: {}", shelterDTO.getSheltername());
                return Optional.empty();
            }
            Shelter shelter = convertToDomain(shelterDTO);
            return shelterDomainService.updateShelter(shelterId, shelter)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error updating shelter", e);
            return Optional.empty();
        }
    }

    public Optional<ShelterDTO> deleteShelter(Integer shelterId) {
        if (shelterId == null) {
            logger.warn("Invalid sheltername");
            return Optional.empty();
        }

        try {
            return shelterDomainService.deleteShelter(shelterId)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error deleting shelter: {}", shelterId, e);
            return Optional.empty();
        }
    }

    private boolean isInvalidShelterData(ShelterDTO shelterDTO) {
        return shelterDTO.getSheltername() == null || shelterDTO.getSheltername().isBlank() ||
                shelterDTO.getPassword() == null || shelterDTO.getPassword().isBlank();
    }

    private Shelter convertToDomain(ShelterDTO shelterDTO) {
        System.out.println(shelterDTO.getSheltername());
        return new Shelter(
                shelterDTO.getId(), // Include ID
                shelterDTO.getSheltername(),
                shelterDTO.getPassword(),
                shelterDTO.getEmail(),
                shelterDTO.getPhone()
        );
    }

    private ShelterDTO convertToDTO(Shelter shelter) {
        System.out.println(shelter.getSheltername());
        return new ShelterDTO(
                shelter.getId(),
                shelter.getSheltername(),
                shelter.getPassword(),
                shelter.getEmail(),
                shelter.getPhone()
        );
    }

    private List<ShelterDTO> convertAllToDTO(List<Shelter> shelters) {
        if (shelters == null) {
            return Collections.emptyList();
        }
        return shelters.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}