package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_records")
public class Medical_records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_records;

    @Column(length = 100)
    private String medical_condition;

    @Column(name = "date_of_examination", nullable = false)
    private Date date_of_examination;

    @Column(name = "vaccinations", length = 100, nullable = false)
    private String vaccinations;

    @OneToOne
    @JoinColumn(name = "id_pet", nullable = false, unique = true)
    private Pet pet;


    public Medical_records(String medicalCondition, Date dateOfExamination, String vaccinations, Pet pet) {
        this.medical_condition = medicalCondition;
        this.date_of_examination = dateOfExamination;
        this.vaccinations = vaccinations;
        this.pet = pet;
    }
}
