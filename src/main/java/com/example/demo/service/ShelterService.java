package com.example.demo.service;


import com.example.demo.model.Shelter;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    Optional<Shelter> findById(Integer id);
    List<Shelter> findAll();
}
