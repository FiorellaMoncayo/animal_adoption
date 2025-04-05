//package com.example.animal_adoptation.infrastructure.service;
//
//import com.example.animal_adoptation.domain.models.Animal;
//import com.example.animal_adoptation.domain.models.Shelter;
//import com.example.animal_adoptation.infrastructure.repositories.AnimalRepository;
//import com.example.animal_adoptation.infrastructure.repositories.ShelterRepository;
//
//import java.util.Optional;
//
//public class ShelterPersistenceService {
//    private final ShelterRepository shelterRepository;
//
//    public ShelterPersistenceService(ShelterRepository shelterRepository) {
//        this.shelterRepository = shelterRepository;
//    }
//
//    public Optional<Shelter> findByUsername(Integer id) {
//        return shelterRepository.findByShelterName (id);
//    }
//}
