package com.example.demo.service.impl;

import com.example.demo.model.Donations;
import com.example.demo.repository.DonationRepository;
import com.example.demo.service.DonationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationsServiceImpl implements DonationsService {

    private final DonationRepository donationRepository;

    public DonationsServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Optional<Donations> findById(Integer id) {
        return donationRepository.findById(id);
    }

    @Override
    public List<Donations> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public void save(Donations donations) {
        donationRepository.save(donations);
    }
}
