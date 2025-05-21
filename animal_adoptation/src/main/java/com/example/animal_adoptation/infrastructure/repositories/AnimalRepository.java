package com.example.animal_adoptation.infrastructure.repositories;

import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<AnimalBBD, Integer> {
    Optional<AnimalBBD> findByReiac(int reiac);
    Optional<AnimalBBD> findByName(String name);
    List<AnimalBBD> findByShelterId(Integer shelterId);

    @Modifying
    @Transactional
    @Query("UPDATE AnimalBBD u SET u.reiac = :reiac, u.name = :name WHERE u.id = :id")
    int updateAnimal(@Param("id") Integer id,
                     @Param("reiac") int reiac,
                     @Param("name") String name);
}