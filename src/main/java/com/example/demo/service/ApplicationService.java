package com.example.demo.service;

import com.example.demo.model.Application;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Optional<Application> findById(Integer id);
    List<Application> findAll();
    void saveApplication(Application application);
    void allowAdoptionAndUpdateApplication(Integer applicationId, String staffPin);
}
