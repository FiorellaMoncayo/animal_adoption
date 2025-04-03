package com.example.animal_adoptation.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_adoptation.application.DTO.AnimalDTO;
import com.example.animal_adoptation.application.service.AnimalApplicationService;
import com.example.animal_adoptation.rest.api.AnimalApi;


@RestController
@RequestMapping("/animal")
public class AnimalController implements AnimalApi {

	private final AnimalApplicationService animalService;

	public AnimalController(AnimalApplicationService animalService) {
		this.animalService = animalService;
	}

	@Override
	public ResponseEntity<AnimalDTO> findByName(String name) {
		return animalService.findByName(name).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<AnimalDTO> createAnimal(@RequestBody AnimalDTO animalDTO) {
		if (animalDTO.getReiac() == null || animalDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return animalService.createAnimal(animalDTO)
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(createdUser))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}

	@Override
	public ResponseEntity<AnimalDTO> findByReiac(Integer reiac) {
		return animalService.findByReiac(reiac).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}
	
}
