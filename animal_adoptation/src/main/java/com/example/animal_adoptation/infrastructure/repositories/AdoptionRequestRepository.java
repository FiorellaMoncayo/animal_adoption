package com.example.animal_adoptation.infrastructure.repositories;

import com.example.animal_adoptation.infrastructure.entities.AdoptionRequestBBD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequestBBD, Integer> {
    List<AdoptionRequestBBD> findByShelterId(Integer shelterId);

    List<AdoptionRequestBBD> findByUserId(Integer userId);
}