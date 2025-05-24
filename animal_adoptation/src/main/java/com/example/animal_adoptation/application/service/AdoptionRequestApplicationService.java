package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.AdoptionRequestDTO;
import com.example.animal_adoptation.application.DTO.CreateAdoptionRequestDTO;
import com.example.animal_adoptation.domain.models.AdoptionRequest;
import com.example.animal_adoptation.domain.service.AdoptionRequestDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestApplicationService {

    private final AdoptionRequestDomainService adoptionRequestDomainService;

    public AdoptionRequestApplicationService(AdoptionRequestDomainService adoptionRequestDomainService) {
        this.adoptionRequestDomainService = adoptionRequestDomainService;
    }

    public Optional<AdoptionRequestDTO> createAdoptionRequest(CreateAdoptionRequestDTO createRequestDTO) {
        if (createRequestDTO.getUserId() == null || createRequestDTO.getAnimalId() == null || createRequestDTO.getShelterId() == null) {
            throw new IllegalArgumentException("Los IDs de usuario, animal y refugio son obligatorios.");
        }
        AdoptionRequest newRequest = new AdoptionRequest(
                createRequestDTO.getUserId(),
                createRequestDTO.getAnimalId(),
                createRequestDTO.getShelterId()
        );
        return adoptionRequestDomainService.createAdoptionRequest(newRequest)
                .map(AdoptionRequestDTO::new);
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
}