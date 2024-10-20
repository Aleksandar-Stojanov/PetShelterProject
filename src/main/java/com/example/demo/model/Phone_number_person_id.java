package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
@Embeddable
public class Phone_number_person_id implements Serializable {

    @ManyToOne
    @JoinColumn(name = "personal_identification_number")
    private Person person;

    @Column(length = 30, nullable = false)
    private String person_phone_number;
}