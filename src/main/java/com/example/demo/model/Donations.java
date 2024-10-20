package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donations")
public class Donations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_donations;

    @Column(length = 20, nullable = false)
    private String personal_identification_number;

    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private Shelter shelter;

    // Constructors, getters, setters...

    // Default constructor
    public Donations() {
    }

    // Constructor with all fields
    public Donations(String personal_identification_number, Shelter shelter) {
        this.personal_identification_number = personal_identification_number;
        this.shelter = shelter;
    }

    // Getters and setters

    // Other methods as needed
}
