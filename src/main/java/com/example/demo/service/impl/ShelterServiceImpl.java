package com.example.demo.service.impl;

import com.example.demo.model.Shelter;
import com.example.demo.repository.ShelterRepository;
import com.example.demo.service.ShelterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Optional<Shelter> findById(Integer id) {
        return shelterRepository.findById(id);
    }

    @Override
    public List<Shelter> findAll() {
        return shelterRepository.findAll();
    }
}
