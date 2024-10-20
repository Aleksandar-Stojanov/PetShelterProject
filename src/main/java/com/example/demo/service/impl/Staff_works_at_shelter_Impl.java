package com.example.demo.service.impl;

import com.example.demo.model.Staff_works_at_shelter;
import com.example.demo.model.Staff_works_at_shelter_id;
import com.example.demo.repository.Staff_works_at_shelter_Repository;
import com.example.demo.service.Staff_works_at_shelter_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Staff_works_at_shelter_Impl implements Staff_works_at_shelter_Service {

    private final Staff_works_at_shelter_Repository staffWorksAtShelterRepository;

    public Staff_works_at_shelter_Impl(Staff_works_at_shelter_Repository staffWorksAtShelterRepository) {
        this.staffWorksAtShelterRepository = staffWorksAtShelterRepository;
    }

    @Override
    public Optional<Staff_works_at_shelter> findById(Staff_works_at_shelter_id id) {
        return staffWorksAtShelterRepository.findById(id);
    }

    @Override
    public List<Staff_works_at_shelter> findAll() {
        return staffWorksAtShelterRepository.findAll();
    }
}
