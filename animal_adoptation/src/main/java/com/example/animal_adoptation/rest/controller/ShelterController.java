package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.DTO.ShelterDTO;
import com.example.animal_adoptation.application.service.ShelterApplicationService;
import com.example.animal_adoptation.rest.api.ShelterApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/shelters")
public class ShelterController implements ShelterApi {
    private final ShelterApplicationService shelterService;

    public ShelterController(ShelterApplicationService shelterService){
        this.shelterService= shelterService;
    }

    @Override
    public ResponseEntity<ShelterDTO> login(@RequestBody ShelterDTO shelterDTO) {
        if(shelterDTO.getSheltername() == null || shelterDTO.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        return shelterService.authenticate(shelterDTO.getSheltername(), shelterDTO.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @Override
    public ResponseEntity<ShelterDTO> findBysheltername(@PathVariable String sheltername) {
        return shelterService.findBysheltername(sheltername)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<ShelterDTO> createShelter(@RequestBody ShelterDTO shelterDTO) {
        if (shelterDTO.getSheltername() == null || shelterDTO.getSheltername().isEmpty() ||
                shelterDTO.getPassword() == null || shelterDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return shelterService.createShelter(shelterDTO)
                .map(createdShelter -> ResponseEntity.status(HttpStatus.CREATED).body(createdShelter))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public ResponseEntity<ShelterDTO> updateShelter(@PathVariable String sheltername, @RequestBody ShelterDTO shelterDTO) {
        if (shelterDTO.getSheltername() == null || shelterDTO.getSheltername().isBlank() ||
                shelterDTO.getPassword() == null || shelterDTO.getPassword().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        // Find shelter by sheltername to get ID
        Optional<ShelterDTO> existingShelter = shelterService.findBysheltername(sheltername);
        if (existingShelter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        shelterDTO.setId(existingShelter.get().getId()); // Set ID in DTO
        return shelterService.updateShelter(shelterDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteShelter(@PathVariable Integer shelterId) {
        if (shelterId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<ShelterDTO> result = shelterService.deleteShelter(shelterId);
        return result.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}



