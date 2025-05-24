package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.domain.models.AdoptionRequest;

import java.util.List;
import java.util.Optional;

public interface AdoptionRequestRepositoryPort {
    Optional<AdoptionRequest> createAdoptionRequest(AdoptionRequest request);
    Optional<AdoptionRequest> findById(Integer id);
    List<AdoptionRequest> findByShelterId(Integer shelterId);
    List<AdoptionRequest> findByUserId(Integer userId);
    Optional<AdoptionRequest> updateAdoptionRequest(AdoptionRequest request);
    boolean deleteAdoptionRequest(Integer id);
}