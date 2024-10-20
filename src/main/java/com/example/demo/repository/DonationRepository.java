package com.example.demo.repository;

import com.example.demo.model.Donations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donations,Integer> {
}
