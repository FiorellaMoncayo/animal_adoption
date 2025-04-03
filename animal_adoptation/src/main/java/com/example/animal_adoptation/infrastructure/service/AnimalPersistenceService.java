package com.example.animal_adoptation.infrastructure.service;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.AnimalRepository;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AnimalPersistenceService {

    private final AnimalRepository animalRepository;

    public AnimalPersistenceService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    
    public AnimalBBD save(AnimalBBD animal) {
        return animalRepository.save(animal);
    }
    
    public Optional<Animal> findByReiac(Integer reiac) {
        return animalRepository.findByReiac (reiac);
    }
    
    public Optional<Animal> findByName(String name) {
        return animalRepository.findByName(name);
    }
    
    public AnimalBBD createAnimal(AnimalBBD animalBBD) {
        if (animalRepository.findByReiac(animalBBD.getReiac()).isPresent()) {
            throw new RuntimeException("Animal already exists");
        }
        return animalRepository.save(animalBBD);
    }
}