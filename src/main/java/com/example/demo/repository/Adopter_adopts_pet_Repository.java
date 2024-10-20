package com.example.demo.repository;

import com.example.demo.model.AdopterAdoptsPetId;
import com.example.demo.model.Adopter_adopts_pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Adopter_adopts_pet_Repository extends JpaRepository<Adopter_adopts_pet, AdopterAdoptsPetId> {
}
