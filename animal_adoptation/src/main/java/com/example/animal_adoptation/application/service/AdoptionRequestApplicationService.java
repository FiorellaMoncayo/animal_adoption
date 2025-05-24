package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.AdoptionRequestDTO;
import com.example.animal_adoptation.application.DTO.CreateAdoptionRequestDTO;
import com.example.animal_adoptation.domain.models.AdoptionRequest;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.service.AdoptionRequestDomainService;
import com.example.animal_adoptation.domain.service.AnimalDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(AdoptionRequestApplicationService.class);

    private final AdoptionRequestDomainService adoptionRequestDomainService;
    private final AnimalDomainService animalDomainService;

    public AdoptionRequestApplicationService(AdoptionRequestDomainService adoptionRequestDomainService, AnimalDomainService animalDomainService) {
        this.adoptionRequestDomainService = adoptionRequestDomainService;
        this.animalDomainService = animalDomainService;
    }

    public Optional<AdoptionRequestDTO> createAdoptionRequest(CreateAdoptionRequestDTO createAdoptionRequestDTO) {
        if (createAdoptionRequestDTO == null || createAdoptionRequestDTO.getUserId() == null ||
                createAdoptionRequestDTO.getAnimalId() == null) {
            logger.warn("Invalid input for creating adoption request: userId or animalId is null.");
            return Optional.empty();
        }

        // 1. Obtener el animal para verificar su existencia y obtener el shelterId
        Optional<Animal> animalOptional = animalDomainService.findById(createAdoptionRequestDTO.getAnimalId());
        if (animalOptional.isEmpty()) {
            logger.warn("Animal with ID {} not found for adoption request.", createAdoptionRequestDTO.getAnimalId());
            return Optional.empty();
        }
        Animal animal = animalOptional.get();
        Integer shelterId = animal.getShelterId();

        // 2. Construir el modelo de dominio de AdoptionRequest
        AdoptionRequest adoptionRequest = AdoptionRequest.builder()
                .userId(createAdoptionRequestDTO.getUserId())
                .animalId(createAdoptionRequestDTO.getAnimalId())
                .shelterId(shelterId)
                .status(AdoptionRequest.AdoptionStatus.PENDING)
                .build();

        try {
            return adoptionRequestDomainService.createAdoptionRequest(adoptionRequest)
                    .map(this::convertToDTO);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Data integrity violation creating adoption request: {}", e.getMessage());
            return Optional.empty();
        } catch (RuntimeException e) {
            logger.error("Error creating adoption request", e);
            return Optional.empty();
        }
    }

    public Optional<AdoptionRequestDTO> getAdoptionRequestById(Integer id) {
        return adoptionRequestDomainService.getAdoptionRequestById(id)
                .map(AdoptionRequestDTO::new);
    }

    public List<AdoptionRequestDTO> getAdoptionRequestsByShelterId(Integer shelterId) {
        return adoptionRequestDomainService.getAdoptionRequestsByShelterId(shelterId)
                .stream()
                .map(AdoptionRequestDTO::new)
                .collect(Collectors.toList());
    }

    public List<AdoptionRequestDTO> getAdoptionRequestsByUserId(Integer userId) {
        return adoptionRequestDomainService.getAdoptionRequestsByUserId(userId)
                .stream()
                .map(AdoptionRequestDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AdoptionRequestDTO> updateAdoptionRequestStatus(Integer requestId, String newStatus) {
        if (requestId == null || newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("ID de solicitud y nuevo estado son obligatorios.");
        }
        return adoptionRequestDomainService.updateAdoptionRequestStatus(requestId, newStatus)
                .map(AdoptionRequestDTO::new);
    }

    public boolean deleteAdoptionRequest(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de solicitud es obligatorio.");
        }
        return adoptionRequestDomainService.deleteAdoptionRequest(id);
    }

    private AdoptionRequestDTO convertToDTO(AdoptionRequest adoptionRequest) {
        if (adoptionRequest == null) {
            return null;
        }
        return AdoptionRequestDTO.builder()
                .id(adoptionRequest.getId())
                .userId(adoptionRequest.getUserId())
                .animalId(adoptionRequest.getAnimalId())
                .shelterId(adoptionRequest.getShelterId())
                .status(adoptionRequest.getStatus().name())
                .requestDate(adoptionRequest.getRequestDate())
                .build();
    }
}