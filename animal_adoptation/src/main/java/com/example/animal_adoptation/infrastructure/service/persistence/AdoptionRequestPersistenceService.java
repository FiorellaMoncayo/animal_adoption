package com.example.animal_adoptation.infrastructure.service.persistence;

import com.example.animal_adoptation.domain.models.AdoptionRequest;
import com.example.animal_adoptation.domain.port.AdoptionRequestRepositoryPort; // Crearemos esta interfaz a continuación
import com.example.animal_adoptation.infrastructure.entities.AdoptionRequestBBD;
import com.example.animal_adoptation.infrastructure.repositories.AdoptionRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestPersistenceService implements AdoptionRequestRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(AdoptionRequestPersistenceService.class);
    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestPersistenceService(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    @Override
    public Optional<AdoptionRequest> createAdoptionRequest(AdoptionRequest request) {
        try {
            AdoptionRequestBBD entity = convertToEntity(request);
            AdoptionRequestBBD savedEntity = adoptionRequestRepository.save(entity);
            return Optional.of(convertToDomain(savedEntity));
        } catch (DataIntegrityViolationException e) {
            logger.error("Error de integridad al crear solicitud de adopción: {}", e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error al crear solicitud de adopción: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdoptionRequest> findById(Integer id) {
        return adoptionRequestRepository.findById(id)
                .map(this::convertToDomain);
    }

    @Override
    public List<AdoptionRequest> findByShelterId(Integer shelterId) {
        return adoptionRequestRepository.findByShelterId(shelterId)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdoptionRequest> findByUserId(Integer userId) {
        return adoptionRequestRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdoptionRequest> updateAdoptionRequest(AdoptionRequest request) {
        if (request.getId() == null) {
            logger.warn("ID de solicitud nulo para actualización.");
            return Optional.empty();
        }
        Optional<AdoptionRequestBBD> existingEntity = adoptionRequestRepository.findById(request.getId());
        if (existingEntity.isEmpty()) {
            logger.warn("Solicitud de adopción no encontrada para ID: {}", request.getId());
            return Optional.empty();
        }

        try {
            // Actualizar solo los campos que se pueden modificar (ej. status)
            AdoptionRequestBBD entityToUpdate = existingEntity.get();
            entityToUpdate.setStatus(request.getStatus());
            // No se debería permitir cambiar userId, animalId, shelterId ni requestDate una vez creada.

            AdoptionRequestBBD updatedEntity = adoptionRequestRepository.save(entityToUpdate);
            return Optional.of(convertToDomain(updatedEntity));
        } catch (Exception e) {
            logger.error("Error al actualizar solicitud de adopción: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteAdoptionRequest(Integer id) {
        if (adoptionRequestRepository.existsById(id)) {
            adoptionRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- Métodos de Conversión ---
    private AdoptionRequestBBD convertToEntity(AdoptionRequest domain) {
        AdoptionRequestBBD entity = new AdoptionRequestBBD();
        entity.setId(domain.getId());
        entity.setUserId(domain.getUserId());
        entity.setAnimalId(domain.getAnimalId());
        entity.setShelterId(domain.getShelterId());
        entity.setStatus(domain.getStatus());
        entity.setRequestDate(domain.getRequestDate());
        return entity;
    }

    private AdoptionRequest convertToDomain(AdoptionRequestBBD entity) {
        return new AdoptionRequest(
                entity.getId(),
                entity.getUserId(),
                entity.getAnimalId(),
                entity.getShelterId(),
                entity.getStatus(),
                entity.getRequestDate()
        );
    }
}