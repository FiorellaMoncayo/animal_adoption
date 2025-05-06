package com.example.animal_adoptation.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;

import jakarta.transaction.Transactional;

public interface AnimalRepository extends JpaRepository<AnimalBBD, Integer>{
	
	Optional<AnimalBBD> findByReiac(int reiac);
	Optional<AnimalBBD> findByName(String name);
    //Optional<Animal> createAnimal(AnimalBBD animalBBD);
	
	@Modifying
    @Transactional
    @Query("UPDATE AnimalBBD u SET u.reiac = :reiac, u.name = :name WHERE u.id = :id")
    int updateAnimal(@Param("id") Integer id,
				    @Param("reiac") int reiac,
				    @Param("name") String name);
	
}
