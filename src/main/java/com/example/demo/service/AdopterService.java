package com.example.demo.service;

import com.example.demo.model.Adopter;

import java.util.List;
import java.util.Optional;

public interface AdopterService {
    Optional<Adopter> findById(String id);
    List<Adopter> findAll();
    void save(Adopter adopter);
}
