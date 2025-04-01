package com.example.animal_adoptation.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.animal_adoptation.infrastructure.entities.ShelterBBD;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterBBD, Integer>{
	Optional <ShelterBBD> findBySheltername (String sheltername);
}
