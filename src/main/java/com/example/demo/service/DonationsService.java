package com.example.demo.service;
import com.example.demo.model.Donations;

import java.util.List;
import java.util.Optional;

public interface DonationsService {
    Optional<Donations> findById(Integer id);
    List<Donations> findAll();
    void save(Donations donations);
}
