package com.example.demo.service;

import com.example.demo.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    Optional<Staff> findById(String id);
    List<Staff> findAll();
}
