package com.example.animal_adoptation.infrastructure.repositories;

import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBBD, Integer> {
    //Optional<UserBBD> findById (Integer id);
    Optional <UserBBD> findByUsername (String username);
}