package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.animal_adoptation.domain.service.AnimalDomainService;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.infrastructure.repositories.ShelterRepository;
import com.example.animal_adoptation.infrastructure.service.persistence.AnimalPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class AnimalDomainServiceImpl implements AnimalDomainService {

    private final AnimalPersistenceService animalPersistenceService;
    private final ShelterRepository shelterRepository;

    public AnimalDomainServiceImpl(AnimalPersistenceService animalPersistenceService, ShelterRepository shelterRepository) {
        this.animalPersistenceService = animalPersistenceService;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Optional<List<Animal>> getAllAnimals() {
        return animalPersistenceService.getAllAnimals()
                .map(animals -> animals.isEmpty() ? Collections.<Animal>emptyList() : animals);
    }

    @Override
    public Optional<List<Animal>> getAllShelterAnimals(Integer id) {
        if (id == null || !shelterRepository.existsById(id)) {
            return Optional.empty();
        }
        return animalPersistenceService.findByShelter(id)
                .map(animals -> animals.isEmpty() ? Collections.<Animal>emptyList() : animals);
    }

    @Override
    public Optional<Animal> findByReiac(int reiac) {
        return animalPersistenceService.findByReiac(reiac);
    }

    @Override
    public Optional<Animal> findByName(String name) {
        return animalPersistenceService.findByName(name);
    }

    @Override
    public Optional<Animal> createAnimal(Animal animal) {
        if (animal == null || animal.getShelter() == null || animal.getShelter().getId() == null ||
            !shelterRepository.existsById(animal.getShelter().getId())) {
            throw new IllegalArgumentException("Invalid or non-existent shelter");
        }
        return animalPersistenceService.createAnimal(animal);
    }

    @Override
    public Optional<Animal> updateAnimal(Animal animal) {
        if (animal == null || animal.getReiac() == 0 || animal.getName() == null || animal.getName().isBlank() ||
            animal.getShelter() == null || animal.getShelter().getId() == null ||
            !shelterRepository.existsById(animal.getShelter().getId())) {
            throw new IllegalArgumentException("Invalid animal data or non-existent shelter");
        }
        return animalPersistenceService.updateAnimal(animal);
    }

    @Override
    public Optional<Animal> deleteAnimal(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        return animalPersistenceService.deleteAnimal(id);
    }
}