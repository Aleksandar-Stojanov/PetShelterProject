package com.example.demo.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class Staff_works_at_shelter_id implements Serializable {
    @ManyToOne
    @JoinColumn(name = "personal_identification_number")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private Shelter shelter;

    public Staff_works_at_shelter_id(Staff staff, Shelter shelter) {
        this.staff = staff;
        this.shelter = shelter;
    }
}
