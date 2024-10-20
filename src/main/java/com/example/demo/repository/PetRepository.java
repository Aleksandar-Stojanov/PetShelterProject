package com.example.demo.repository;

import com.example.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<Pet,Integer> {
    @Modifying
    @Query("UPDATE Pet p SET p.adoption_status = :newAdoptionStatus WHERE p.id_pet = :petId")
    void updateAdoptionStatus(@Param("petId") Integer petId, @Param("newAdoptionStatus") String newAdoptionStatus);
}
