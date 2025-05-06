package com.example.animal_adoptation.infrastructure.service.persistence;

import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.domain.port.AnimalRepositoryPort;
import com.example.animal_adoptation.infrastructure.entities.AnimalBBD;
import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import com.example.animal_adoptation.infrastructure.repositories.AnimalRepository;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AnimalPersistenceService implements AnimalRepositoryPort{
	public static final Logger logger = LoggerFactory.getLogger(AnimalPersistenceService.class);
    private final AnimalRepository animalRepository;

    public AnimalPersistenceService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    
    @Override
    public Animal save(Animal domainAnimal) {
        try {
            AnimalBBD entity = convertToEntity(domainAnimal);
            AnimalBBD savedEntity = animalRepository.save(entity);
            return convertToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving animal: {}", e.getMessage());
            throw new IllegalArgumentException("User data violation rules", e);
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
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return animalRepository.findByName(name)
                .map(this::convertToDomain);
    }
    
    @Override
    public Optional<Animal>  createAnimal(Animal animal) {
    	if (animalRepository.findByReiac(animal.getReiac()).isPresent()) {
            throw new DataIntegrityViolationException("Animal already exists");
        }
        try {
            Animal createdAnimal = save(animal);
            return Optional.of(createdAnimal);
        } catch (Exception e) {
            //logger.error("Error creating user: {}", e.getMessage());
            return Optional.empty();
        }
    }

	@Override
	public Optional<Animal> updateAnimal(Animal animal) {
		try {
            Optional<AnimalBBD> existingAnimal = animalRepository.findById(animal.getId());
            if (existingAnimal.isEmpty()) {
                logger.warn("Animal not found for reiac: {}", animal.getReiac());
                return Optional.empty();
            }

            int rowsAffected = animalRepository.updateAnimal(animal.getId(), animal.getReiac(), animal.getName());
            if (rowsAffected == 0) {
                return Optional.empty();
            }

            return animalRepository.findById(animal.getId())
                    .map(this::convertToDomain);
        } catch (Exception e) {
            logger.error("Error updating animal: {}", e.getMessage());
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
    	AnimalBBD entity = new AnimalBBD();
        entity.setId(domain.getId());
        entity.setReiac(domain.getReiac());
        entity.setName(domain.getName());
        return entity;
    }

    private Animal convertToDomain(AnimalBBD entity) {
        return new Animal(entity.getId(), entity.getReiac(), entity.getName());
    }


    
}