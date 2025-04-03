package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.domain.models.Animal;

import java.util.Optional;

public interface AnimalRepositoryPort {
	
    Optional<Animal> findByReiac(Integer reiac);
    Optional<Animal> findByName(String name);
    Optional<Animal> createAnimal(AnimalDTO animalDTO);
}
