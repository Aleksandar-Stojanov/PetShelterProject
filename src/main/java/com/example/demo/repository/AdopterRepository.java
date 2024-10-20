package com.example.demo.repository;

import com.example.demo.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter,String> {
}
