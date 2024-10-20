package com.example.demo.service;

import com.example.demo.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Optional<Pet> findById(Integer id);
    List<Pet> findAll();
    void updatePetAdoptionStatus(Integer petId, String newAdoptionStatus);

    void save(Pet pet);
}
