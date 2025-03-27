package com.example.animal_adoptation.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AnimalBBD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reiac")
    private int reiac;
    @Column(name="name")
    private String name;
}
