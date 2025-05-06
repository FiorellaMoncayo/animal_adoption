package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.ShelterDTO;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.domain.models.Shelter;
import com.example.animal_adoptation.domain.service.ShelterDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShelterApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ShelterApplicationService.class);

    private final ShelterDomainService shelterDomainService;

    public ShelterApplicationService(ShelterDomainService shelterDomainService) {
        this.shelterDomainService = shelterDomainService;
    }

    public Optional<ShelterDTO> authenticate(String sheltername, String password) {
        return shelterDomainService.authenticate(sheltername, password)
                .map(shelter -> new ShelterDTO(shelter.getId(), shelter.getSheltername(), null));
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

    public Optional<ShelterDTO> updateShelter(ShelterDTO shelterDTO) {
        if (shelterDTO == null || isInvalidShelterData(shelterDTO)) {
            logger.warn("Invalid or incomplete shelter data");
            return Optional.empty();
        }

        try {
            Shelter shelter = convertToDomain(shelterDTO);
            return shelterDomainService.updateShelter(shelter)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error updating shelter", e);
            return Optional.empty();
        }
    }

    public Optional<ShelterDTO> deleteShelter(String sheltername) {
        if (sheltername == null || sheltername.isBlank()) {
            logger.warn("Invalid sheltername");
            return Optional.empty();
        }

        try {
            return shelterDomainService.deleteShelter(sheltername)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error deleting shelter: {}", sheltername, e);
            return Optional.empty();
        }
    }

    private boolean isInvalidShelterData(ShelterDTO shelterDTO) {
        return shelterDTO.getSheltername() == null || shelterDTO.getSheltername().isBlank() ||
                shelterDTO.getPassword() == null || shelterDTO.getPassword().isBlank();
    }

    private Shelter convertToDomain(ShelterDTO shelterDTO) {
        return new Shelter(
                null,
                shelterDTO.getSheltername(),
                shelterDTO.getPassword()
        );
    }

    private ShelterDTO convertToDTO(Shelter shelter) {
        return new ShelterDTO(
                shelter.getId(),
                shelter.getSheltername(),
                shelter.getPassword()
        );
    }
}
