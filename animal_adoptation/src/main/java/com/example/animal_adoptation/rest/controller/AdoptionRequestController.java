package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.DTO.AdoptionRequestDTO;
import com.example.animal_adoptation.application.DTO.CreateAdoptionRequestDTO;
import com.example.animal_adoptation.application.service.AdoptionRequestApplicationService;
import com.example.animal_adoptation.rest.api.AdoptionRequestApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/adoption_requests")
public class AdoptionRequestController implements AdoptionRequestApi {

    private final AdoptionRequestApplicationService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestApplicationService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @Override
    public ResponseEntity<AdoptionRequestDTO> createAdoptionRequest(@RequestBody CreateAdoptionRequestDTO createRequestDTO) {
        try {
            return adoptionRequestService.createAdoptionRequest(createRequestDTO)
                    .map(request -> new ResponseEntity<>(request, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // Podría ser si ya existe una solicitud pendiente
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<AdoptionRequestDTO>> getShelterAdoptionRequests(@PathVariable Integer shelterId) {
        List<AdoptionRequestDTO> requests = adoptionRequestService.getAdoptionRequestsByShelterId(shelterId);
        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdoptionRequestDTO>> getUserAdoptionRequests(@PathVariable Integer userId) {
        List<AdoptionRequestDTO> requests = adoptionRequestService.getAdoptionRequestsByUserId(userId);
        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdoptionRequestDTO> updateAdoptionRequestStatus(
            @PathVariable String newStatus,
            @RequestBody Integer requestId
    ) {
        try {
            return adoptionRequestService.updateAdoptionRequestStatus(requestId, newStatus)
                    .map(request -> new ResponseEntity<>(request, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAdoptionRequest(@PathVariable Integer id) {
        boolean deleted = adoptionRequestService.deleteAdoptionRequest(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}