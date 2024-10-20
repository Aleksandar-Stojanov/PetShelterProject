package com.example.demo.repository;

import com.example.demo.model.Pet_lives_at_shelter;
import com.example.demo.model.Pet_lives_at_shelter_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pet_lives_at_shelter_Repository extends JpaRepository<Pet_lives_at_shelter, Pet_lives_at_shelter_Id> {
}
