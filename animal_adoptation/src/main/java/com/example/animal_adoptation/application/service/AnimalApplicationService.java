package com.example.animal_adoptation.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.service.AnimalPersistenceService;

@Service
public class AnimalApplicationService {

	private final AnimalPersistenceService animalPersistenceService;

    public AnimalApplicationService(AnimalPersistenceService animalPersistenceService) {
        this.animalPersistenceService = animalPersistenceService;
    }

    public Optional<AnimalDTO> findByReiac (Integer reiac){
        return animalPersistenceService.findByReiac(reiac)
                .map(animal -> new AnimalDTO(animal.getId(), animal.getReiac(), animal.getName()));
    }

    public Optional<AnimalDTO> findByName (String name){
        return animalPersistenceService.findByName(name)
                .map(animal -> new AnimalDTO(animal.getId(), animal.getReiac(), animal.getName()));
    }
    
    public Optional<AnimalDTO> createAnimal(AnimalDTO animalDTO) {
        AnimalBBD animalBBD = new AnimalBBD();
        animalBBD.setReiac(animalDTO.getReiac());
        animalBBD.setName(animalDTO.getName());
        AnimalBBD savedAnimal = animalPersistenceService.save(animalBBD);
        return Optional.of(new AnimalDTO(savedAnimal.getId(), savedAnimal.getReiac(), savedAnimal.getName()));

    }
}
