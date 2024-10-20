package com.example.demo.service.impl;

import com.example.demo.model.Adopter_adopts_pet;
import com.example.demo.repository.Adopter_adopts_pet_Repository;
import com.example.demo.service.Adopter_adopts_pet_Service;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class Adopter_adopts_pet_Impl implements Adopter_adopts_pet_Service {
    private final Adopter_adopts_pet_Repository adopterAdoptsPetRepository;

    public Adopter_adopts_pet_Impl(Adopter_adopts_pet_Repository adopterAdoptsPetRepository) {
        this.adopterAdoptsPetRepository = adopterAdoptsPetRepository;
    }

    @Override
    @Transactional
    public void save(Adopter_adopts_pet adopterAdoptsPet) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.sql.Date currentDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());
        adopterAdoptsPet.setAdoption_date(currentDate);
        adopterAdoptsPetRepository.save(adopterAdoptsPet);
    }
}
