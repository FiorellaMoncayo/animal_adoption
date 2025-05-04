package com.example.animal_adoptation.domain.service;

import java.util.Optional;

import com.example.animal_adoptation.domain.models.Animal;

public interface AnimalDomainService {
	
	Optional<Animal> findByReiac(int reiac);
	Optional<Animal> findByName(String name);
    Optional<Animal> createAnimal(Animal animal);
    Optional<Animal> updateAnimal(Animal animal);
    Optional<Animal> deleteAnimal(int reiac);
}
