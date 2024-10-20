package com.example.demo.service.impl;

import com.example.demo.model.Medical_records;
import com.example.demo.repository.Medical_records_Repository;
import com.example.demo.service.Medical_records_Service;
import org.springframework.stereotype.Service;

@Service
public class Medical_records_Impl implements Medical_records_Service {
    private final Medical_records_Repository medicalRecordsRepository;

    public Medical_records_Impl(Medical_records_Repository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
    }

    @Override
    public void save(Medical_records medicalRecords) {
        medicalRecordsRepository.save(medicalRecords);
    }
}
