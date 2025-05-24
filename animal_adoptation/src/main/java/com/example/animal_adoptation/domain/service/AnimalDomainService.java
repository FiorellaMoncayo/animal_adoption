package com.example.animal_adoptation.domain.service;

import com.example.animal_adoptation.domain.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalDomainService {
    Optional<Animal> findByReiac(int reiac);
    Optional<Animal> findByName(String name);
    Optional<Animal> findById(Integer id);
    Optional<Animal> createAnimal(Animal animal);
    Optional<Animal> updateAnimal(Animal animal);
    Optional<Animal> deleteAnimal(Integer id);
    Optional<List<Animal>> getAllAnimals();
    Optional<List<Animal>> getAllShelterAnimals(Integer id);
}