package com.example.animal_adoptation.application.service;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.service.AnimalDomainService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.animal_adoptation.infrastructure.service.persistence.UserPersistenceService.logger;

@Service
public class AnimalApplicationService {
    private final AnimalDomainService animalDomainService;

    public AnimalApplicationService(AnimalDomainService animalDomainService) {
        this.animalDomainService = animalDomainService;
    }

    public List<AnimalDTO> getAllAnimals() {
        return animalDomainService.getAllAnimals()
                .map(this::convertAllToDTO)
                .orElse(Collections.emptyList());
    }

    public List<AnimalDTO> getAllShelterAnimals(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid shelter ID");
        }
        return animalDomainService.getAllShelterAnimals(id)
                .map(this::convertAllToDTO)
                .orElse(Collections.emptyList());
    }

    public Optional<AnimalDTO> findByReiac(Integer reiac) {
        if (reiac == null) {
            throw new IllegalArgumentException("Reiac cannot be empty");
        }
        return animalDomainService.findByReiac(reiac)
                .map(this::convertToDTO);
    }

    public Optional<AnimalDTO> findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return animalDomainService.findByName(name)
                .map(this::convertToDTO);
    }

    public Optional<AnimalDTO> createAnimal(AnimalDTO animalDTO) {
        if (animalDTO == null || animalDTO.getReiac() == 0 ||
                animalDTO.getName() == null || animalDTO.getName().isBlank() ||
                animalDTO.getShelterId() == null) {
            return Optional.empty();
        }
        try {
            Animal animal = convertToDomain(animalDTO);
            return animalDomainService.createAnimal(animal)
                    .map(this::convertToDTO);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Sorry, try another Reiac!: {}", animalDTO.getReiac());
            return Optional.empty();
        } catch (RuntimeException e) {
            logger.error("Error creating animal", e);
            return Optional.empty();
        }
    }

    public Optional<AnimalDTO> updateAnimal(AnimalDTO animalDTO) {
        if (animalDTO == null || animalDTO.getReiac() == 0 ||
                animalDTO.getName() == null || animalDTO.getName().isBlank() ||
                animalDTO.getShelterId() == null) {
            logger.warn("Incomplete animal data");
            return Optional.empty();
        }
        try {
            Animal animal = convertToDomain(animalDTO);
            return animalDomainService.updateAnimal(animal)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error updating animal", e);
            return Optional.empty();
        }
    }

    public Optional<AnimalDTO> deleteAnimal(Integer id) {
        if (id == null) {
            logger.warn("Invalid id");
            return Optional.empty();
        }
        try {
            return animalDomainService.deleteAnimal(id)
                    .map(this::convertToDTO);
        } catch (RuntimeException e) {
            logger.error("Error deleting animal: {}", id, e);
            return Optional.empty();
        }
    }

    private Animal convertToDomain(AnimalDTO animalDTO) {
        return Animal.builder()
                .id(animalDTO.getId())
                .reiac(animalDTO.getReiac())
                .name(animalDTO.getName())
                .shelterId(animalDTO.getShelterId())
                .build();
    }

    private AnimalDTO convertToDTO(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .reiac(animal.getReiac())
                .name(animal.getName())
                .shelterId(animal.getShelterId())
                .build();
    }

    private List<AnimalDTO> convertAllToDTO(List<Animal> animals) {
        if (animals == null) {
            return Collections.emptyList();
        }
        return animals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}