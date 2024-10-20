package com.example.demo.service.impl;

import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
    @Override
    public void updatePetAdoptionStatus(Integer petId, String newAdoptionStatus) {
        petRepository.updateAdoptionStatus(petId, newAdoptionStatus);
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }
}
