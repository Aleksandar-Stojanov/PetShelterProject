package com.example.demo.service.impl;

import com.example.demo.model.Adopter;
import com.example.demo.model.Application;
import com.example.demo.repository.AdopterRepository;
import com.example.demo.service.AdopterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdopterServiceImpl implements AdopterService {

    private final AdopterRepository adopterRepository;

    public AdopterServiceImpl(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    @Override
    public Optional<Adopter> findById(String id) {
        return adopterRepository.findById(id);
    }

    @Override
    public List<Adopter> findAll() {
        return adopterRepository.findAll();
    }

    @Override
    public void save(Adopter adopter) {
        adopterRepository.save(adopter);
    }


}
