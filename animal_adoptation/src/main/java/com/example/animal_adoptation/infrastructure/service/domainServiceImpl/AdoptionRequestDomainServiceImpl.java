package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import com.example.animal_adoptation.domain.models.AdoptionRequest;
import com.example.animal_adoptation.domain.port.AdoptionRequestRepositoryPort;
import com.example.animal_adoptation.domain.service.AdoptionRequestDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestDomainServiceImpl implements AdoptionRequestDomainService {

    private final AdoptionRequestRepositoryPort adoptionRequestRepositoryPort;

    public AdoptionRequestDomainServiceImpl(AdoptionRequestRepositoryPort adoptionRequestRepositoryPort) {
        this.adoptionRequestRepositoryPort = adoptionRequestRepositoryPort;
    }

    @Override
    public Optional<AdoptionRequest> createAdoptionRequest(AdoptionRequest request) {
        return adoptionRequestRepositoryPort.createAdoptionRequest(request);
    }

    @Override
    public Optional<AdoptionRequest> getAdoptionRequestById(Integer id) {
        return adoptionRequestRepositoryPort.findById(id);
    }

    @Override
    public List<AdoptionRequest> getAdoptionRequestsByShelterId(Integer shelterId) {
        return adoptionRequestRepositoryPort.findByShelterId(shelterId);
    }

    @Override
    public List<AdoptionRequest> getAdoptionRequestsByUserId(Integer userId) {
        return adoptionRequestRepositoryPort.findByUserId(userId);
    }

    @Override
    public Optional<AdoptionRequest> updateAdoptionRequestStatus(Integer requestId, String newStatusString) { // Cambiado a newStatusString
        Optional<AdoptionRequest> existingRequest = adoptionRequestRepositoryPort.findById(requestId);
        if (existingRequest.isEmpty()) {
            return Optional.empty();
        }

        AdoptionRequest requestToUpdate = existingRequest.get();
        AdoptionRequest.AdoptionStatus newStatusEnum;

        try {
            // Convertir el String de entrada a nuestro Enum
            newStatusEnum = AdoptionRequest.AdoptionStatus.valueOf(newStatusString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido proporcionado: " + newStatusString, e);
        }

        // Lógica de validación de transición de estado, ahora trabajando con ENUMS
        if (!isValidStatusTransition(requestToUpdate.getStatus(), newStatusEnum)) {
            throw new IllegalArgumentException("Transición de estado inválida de " + requestToUpdate.getStatus().name() + " a " + newStatusEnum.name());
        }

        requestToUpdate.setStatus(newStatusEnum); // Asignar directamente el ENUM
        return adoptionRequestRepositoryPort.updateAdoptionRequest(requestToUpdate);
    }

    private boolean isValidStatusTransition(AdoptionRequest.AdoptionStatus currentStatus, AdoptionRequest.AdoptionStatus newStatus) {
        // PENDING solo puede ir a ACCEPTED o REJECTED
        if (currentStatus == AdoptionRequest.AdoptionStatus.PENDING) {
            return newStatus == AdoptionRequest.AdoptionStatus.ACCEPTED ||
                    newStatus == AdoptionRequest.AdoptionStatus.REJECTED;
        }

        if (currentStatus == AdoptionRequest.AdoptionStatus.ACCEPTED ||
                currentStatus == AdoptionRequest.AdoptionStatus.REJECTED) {
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteAdoptionRequest(Integer id) {
        return adoptionRequestRepositoryPort.deleteAdoptionRequest(id);
    }
}