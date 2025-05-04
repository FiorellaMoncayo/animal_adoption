package com.example.animal_adoptation.infrastructure.service.domainServiceImpl;

import java.util.Optional;

import com.example.animal_adoptation.domain.service.AnimalDomainService;
import com.example.animal_adoptation.domain.models.Animal;
import com.example.animal_adoptation.infrastructure.service.persistence.AnimalPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class AnimalDomainServiceImpl implements AnimalDomainService{

	private final AnimalPersistenceService animalPersistenceService;

    public AnimalDomainServiceImpl(AnimalPersistenceService animalPersistenceService) {
        this.animalPersistenceService = animalPersistenceService;
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
        return animalPersistenceService.createAnimal(animal);
    }

	@Override
	public Optional<Animal> updateAnimal(Animal animal) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Animal> deleteAnimal(int reiac) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
