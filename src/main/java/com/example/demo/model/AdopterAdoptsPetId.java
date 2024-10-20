package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AdopterAdoptsPetId implements Serializable {
//    @Column(length = 20, nullable = false)
//    private String personalIdentificationNumber;
//    private Integer idPet;

    @ManyToOne
    @JoinColumn(name = "personal_identification_number")
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet pet;

//    public AdopterAdoptsPetId(Adopter adopter, Pet pet) {
//        this.adopter = adopter;
//        this.pet = pet;
//    }
}
