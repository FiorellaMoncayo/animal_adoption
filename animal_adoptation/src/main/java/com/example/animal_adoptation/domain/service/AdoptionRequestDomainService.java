package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.AdoptionRequest;

import java.util.List;
import java.util.Optional;

public interface AdoptionRequestDomainService {
    Optional<AdoptionRequest> createAdoptionRequest(AdoptionRequest request);
    Optional<AdoptionRequest> getAdoptionRequestById(Integer id);
    List<AdoptionRequest> getAdoptionRequestsByShelterId(Integer shelterId);
    List<AdoptionRequest> getAdoptionRequestsByUserId(Integer userId);
    Optional<AdoptionRequest> updateAdoptionRequestStatus(Integer requestId, String newStatus);
    boolean deleteAdoptionRequest(Integer id);
}