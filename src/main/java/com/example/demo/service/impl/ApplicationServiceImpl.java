package com.example.demo.service.impl;

import com.example.demo.model.Application;
import com.example.demo.repository.ApplicationRepository;
import com.example.demo.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Optional<Application> findById(Integer id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public void allowAdoptionAndUpdateApplication(Integer applicationId, String staffPin) {
        applicationRepository.updateStaffPin(applicationId, staffPin);
    }
}
