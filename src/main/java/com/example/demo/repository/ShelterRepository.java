package com.example.demo.repository;

import com.example.demo.model.Pet;
import com.example.demo.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter,Integer> {
}
