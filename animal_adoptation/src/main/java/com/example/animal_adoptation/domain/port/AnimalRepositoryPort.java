package com.example.animal_adoptation.domain.port;

import com.example.animal_adoptation.domain.models.Animal;

import java.util.Optional;

public interface AnimalRepositoryPort {
	
    Optional<Animal> findByReiac(int reiac);
    Optional<Animal> findByName(String name);
    Optional<Animal> createAnimal(Animal animal);
    Animal save(Animal animal);
    Optional<Animal> updateAnimal(Animal animal);
    Optional<Animal> deleteAnimal(Integer id);
}
