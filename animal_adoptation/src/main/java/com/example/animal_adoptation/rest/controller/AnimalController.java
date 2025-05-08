package com.example.animal_adoptation.rest.controller;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.application.DTO.UserDTO;
import com.example.animal_adoptation.application.service.AnimalApplicationService;
import com.example.animal_adoptation.rest.api.AnimalApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalController implements AnimalApi {

    private final AnimalApplicationService animalService;

    public AnimalController(AnimalApplicationService animalService) {
        this.animalService = animalService;
    }

    @Override
    public ResponseEntity<AnimalDTO> findByReiac(int reiac) {
        return animalService.findByReiac(reiac)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<AnimalDTO> findByName(String name) {
        return animalService.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<AnimalDTO>> findByShelter(Integer shelterId) {
        try {
            List<AnimalDTO> animals = animalService.findByShelter(shelterId);
            return ResponseEntity.ok(animals);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<AnimalDTO> createAnimal(AnimalDTO animalDTO) {
        if (animalDTO == null || animalDTO.getReiac() == 0 || animalDTO.getName() == null || animalDTO.getName().isEmpty() ||
                animalDTO.getShelterId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return animalService.createAnimal(animalDTO)
                .map(createdAnimal -> ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<AnimalDTO> updateAnimal(int reiac, String name, AnimalDTO animalDTO) {
        if (animalDTO == null || animalDTO.getReiac() == 0 || animalDTO.getName() == null || animalDTO.getName().isEmpty() ||
                animalDTO.getShelterId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return animalService.updateAnimal(animalDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteAnimal(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<AnimalDTO> result = animalService.deleteAnimal(id);
        return result.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}