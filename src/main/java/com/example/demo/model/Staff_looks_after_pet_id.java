package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
@Embeddable
public class Staff_looks_after_pet_id implements Serializable {
    @ManyToOne
    @JoinColumn(name = "personal_identification_number")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet pet;

}

