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
    public Optional<Animal> findByReiac(int reiac) {
        return animalPersistenceService.findByReiac(reiac);
    }

    @Override
    public Optional<Animal> findByName(String name) {
        return animalPersistenceService.findByName(name);
    }

    @Override
    public Optional<List<Animal>> findByShelter(Integer id) {
        if (id == null || !shelterRepository.existsById(id)) {
            return Optional.empty();
        }
        // Asumimos que AnimalPersistenceService tiene un método para buscar por shelterId
        // Esto requiere una implementación en AnimalPersistenceService
        return Optional.of(animalPersistenceService.findByShelter(id)
                .orElse(Collections.emptyList()));
    }

    @Override
    public Optional<Animal> createAnimal(Animal animal) {
        return animalPersistenceService.createAnimal(animal);
    }

    @Override
    public Optional<Animal> updateAnimal(Animal animal) {
        if (animal.getReiac() == 0 || animal.getName() == null || animal.getName().isBlank() ||
                animal.getShelter() == null) {
            throw new IllegalArgumentException("Animal data incomplete");
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
