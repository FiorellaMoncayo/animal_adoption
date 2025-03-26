package com.example.animal_adoptation.infrastructure.repositories;

import com.example.animal_adoptation.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

}
