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
    public Optional<AdoptionRequest> updateAdoptionRequestStatus(Integer requestId, String newStatus) {
        Optional<AdoptionRequest> existingRequest = adoptionRequestRepositoryPort.findById(requestId);
        if (existingRequest.isEmpty()) {
            return Optional.empty();
        }

        AdoptionRequest requestToUpdate = existingRequest.get();
        // Lógica de validación de transición de estado
        if (!isValidStatusTransition(requestToUpdate.getStatus(), newStatus)) {
            throw new IllegalArgumentException("Transición de estado inválida de " + requestToUpdate.getStatus() + " a " + newStatus);
        }

        requestToUpdate.setStatus(newStatus);
        return adoptionRequestRepositoryPort.updateAdoptionRequest(requestToUpdate);
    }

    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        if (currentStatus.equals("PENDING")) {
            return newStatus.equals("ACCEPTED") || newStatus.equals("REJECTED");
        }
        // Una vez aceptada o rechazada, quizás no se pueda cambiar el estado
        // Por ahora ACCEPTED/REJECTED pueden cambiar entre sí (por si hay un error)
        if (currentStatus.equals("ACCEPTED") || currentStatus.equals("REJECTED")) {
            return newStatus.equals("ACCEPTED") || newStatus.equals("REJECTED");
        }
        return false;
    }

    @Override
    public boolean deleteAdoptionRequest(Integer id) {
        return adoptionRequestRepositoryPort.deleteAdoptionRequest(id);
    }
}