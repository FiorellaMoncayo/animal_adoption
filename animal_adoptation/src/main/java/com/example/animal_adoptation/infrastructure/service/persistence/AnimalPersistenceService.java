package com.example.animal_adoptation.infrastructure.service.persistence;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.port.AnimalRepositoryPort;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.repositories.AnimalRepository;
import com.example.animal_adoptation.infrastructure.repositories.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalPersistenceService implements AnimalRepositoryPort {
    public static final Logger logger = LoggerFactory.getLogger(AnimalPersistenceService.class);
    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;

    public AnimalPersistenceService(AnimalRepository animalRepository, ShelterRepository shelterRepository) {
        this.animalRepository = animalRepository;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Optional<List<Animal>> getAllAnimals() {
        List<AnimalBBD> animals = animalRepository.findAll();
        if (animals.isEmpty()) {
            return Optional.of(Collections.emptyList());
        }
        return Optional.of(animals.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Animal>> findByShelter(Integer shelterId) {
        if (shelterId == null) {
            return Optional.empty();
        }
        List<AnimalBBD> animals = animalRepository.findByShelterId(shelterId);
        if (animals.isEmpty()) {
            return Optional.of(Collections.emptyList());
        }
        return Optional.of(animals.stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList()));
    }

    @Override
    public Animal save(Animal domainAnimal) {
        try {
            if (domainAnimal.getShelterId() == null || !shelterRepository.existsById(domainAnimal.getShelterId())) {
                throw new IllegalArgumentException("Shelter with ID " + domainAnimal.getShelterId() + " not found");
            }
            AnimalBBD entity = convertToEntity(domainAnimal);
            AnimalBBD savedEntity = animalRepository.save(entity);
            return convertToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving animal: reiac={}, shelterId={}: {}", 
                    domainAnimal.getReiac(), domainAnimal.getShelterId(), e.getMessage());
            throw new IllegalArgumentException("Animal data violation: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Animal> findByReiac(int reiac) {
        if (reiac == 0) {
            throw new IllegalArgumentException("Reiac cannot be empty");
        }
        return animalRepository.findByReiac(reiac)
                .map(this::convertToDomain);
    }

    @Override
    public Optional<Animal> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return animalRepository.findByName(name)
                .map(this::convertToDomain);
    }

    @Override
    public Optional<Animal> createAnimal(Animal animal) {
        if (animalRepository.findByReiac(animal.getReiac()).isPresent()) {
            throw new DataIntegrityViolationException("Animal with reiac " + animal.getReiac() + " already exists");
        }
        try {
            Animal createdAnimal = save(animal);
            return Optional.of(createdAnimal);
        } catch (Exception e) {
            logger.error("Error creating animal with reiac {}: {}", animal.getReiac(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Animal> updateAnimal(Animal animal) {
        try {
            Optional<AnimalBBD> existingAnimal = animalRepository.findById(animal.getId());
            if (existingAnimal.isEmpty()) {
                logger.warn("Animal not found for id: {}", animal.getId());
                return Optional.empty();
            }
            int rowsAffected = animalRepository.updateAnimal(
                    animal.getId(),
                    animal.getReiac(),
                    animal.getName()
            );
            if (rowsAffected == 0) {
                return Optional.empty();
            }
            return animalRepository.findById(animal.getId())
                    .map(this::convertToDomain);
        }
        catch (Exception e) {
            logger.error("Error updating animal with id {}: {}", animal.getId(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Animal> deleteAnimal(Integer id) {
        try {
            Optional<AnimalBBD> animal = animalRepository.findById(id);
            if (animal.isPresent()) {
                animalRepository.deleteById(id);
                return Optional.of(convertToDomain(animal.get()));
            }
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error deleting animal {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    private AnimalBBD convertToEntity(Animal domain) {
        return AnimalBBD.builder()
                .id(domain.getId())
                .reiac(domain.getReiac())
                .name(domain.getName())
                .shelterId(domain.getShelterId())
                .build();
    }

    private Animal convertToDomain(AnimalBBD entity) {
        return Animal.builder()
                .id(entity.getId())
                .reiac(entity.getReiac())
                .name(entity.getName())
                .shelterId(entity.getShelterId())
                .build();
    }
}