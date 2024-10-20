package com.example.demo.service;

import com.example.demo.model.Staff_works_at_shelter;
import com.example.demo.model.Staff_works_at_shelter_id;
import com.example.demo.service.impl.Staff_works_at_shelter_Impl;

import java.util.List;
import java.util.Optional;

public interface Staff_works_at_shelter_Service {
    Optional<Staff_works_at_shelter> findById(Staff_works_at_shelter_id id);
    List<Staff_works_at_shelter> findAll();
}
