package com.example.animal_adoptation.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;

public interface AnimalRepository extends JpaRepository<AnimalBBD, Integer>{
	
	Optional<Animal> findByReiac(Integer reiac);
	Optional<Animal> findByName(String name);
    //Optional<Animal> createAnimal(AnimalBBD animalBBD);

}

//package com.example.animal_adoptation.infrastructure.repositories;
//
//import com.example.animal_adoptation.domain.models.Animal;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface AnimalRepository extends JpaRepository<Animal, String> {
//    Optional<Animal> findByReiac (Integer reiac);
//}