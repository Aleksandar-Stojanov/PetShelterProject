package com.example.demo.service.impl;

import com.example.demo.model.Pet_lives_at_shelter;
import com.example.demo.repository.Pet_lives_at_shelter_Repository;
import com.example.demo.service.Pet_lives_at_shelter_Service;
import org.springframework.stereotype.Service;

@Service
public class Pet_lives_at_shelter_Impl implements Pet_lives_at_shelter_Service {

    private final Pet_lives_at_shelter_Repository petLivesAtShelterRepository;

    public Pet_lives_at_shelter_Impl(Pet_lives_at_shelter_Repository petLivesAtShelterRepository) {
        this.petLivesAtShelterRepository = petLivesAtShelterRepository;
    }

    @Override
    public void save(Pet_lives_at_shelter petLivesAtShelter) {
        petLivesAtShelterRepository.save(petLivesAtShelter);
    }
}
