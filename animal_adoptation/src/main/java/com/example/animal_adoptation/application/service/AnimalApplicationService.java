package com.example.animal_adoptation.application.service;

import static com.example.animal_adoptation.infrastructure.service.persistence.UserPersistenceService.logger;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.service.AnimalDomainService;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.service.persistence.AnimalPersistenceService;

@Service
public class AnimalApplicationService {

	private final AnimalDomainService animalDomainService;

    public AnimalApplicationService(AnimalDomainService animalDomainService) {
        this.animalDomainService = animalDomainService;
    }

	/*
	 * public Optional<AnimalDTO> findByReiac (Integer reiac){ return
	 * animalPersistenceService.findByReiac(reiac) .map(animal -> new
	 * AnimalDTO(animal.getId(), animal.getReiac(), animal.getName())); }
	 * 
	 * public Optional<AnimalDTO> findByName (String name){ return
	 * animalPersistenceService.findByName(name) .map(animal -> new
	 * AnimalDTO(animal.getId(), animal.getReiac(), animal.getName())); }
	 */
    
	/*
	 * public Optional<AnimalDTO> createAnimal(AnimalDTO animalDTO) { AnimalBBD
	 * animalBBD = new AnimalBBD(); animalBBD.setReiac(animalDTO.getReiac());
	 * animalBBD.setName(animalDTO.getName()); AnimalBBD savedAnimal =
	 * animalPersistenceService.save(animalBBD); return Optional.of(new
	 * AnimalDTO(savedAnimal.getId(), savedAnimal.getReiac(),
	 * savedAnimal.getName())); }
	 */
    
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
        if (animalDTO == null) {
            return Optional.empty();
        }
        if (animalDTO.getReiac() == null ||
        		animalDTO.getName() == null || animalDTO.getName().isBlank()) {
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
    
    private Animal convertToDomain(AnimalDTO animalDTO) {
        return new Animal(
                null,
                animalDTO.getReiac(),
                animalDTO.getName()
        );
    }

    private AnimalDTO convertToDTO(Animal animal) {
        return new AnimalDTO(
        		animal.getId(),
        		animal.getReiac(),
        		animal.getName()
        );
    }
}
