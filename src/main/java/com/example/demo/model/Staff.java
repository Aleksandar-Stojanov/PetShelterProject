package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "personal_identification_number")
@Table(name = "staff")
@DiscriminatorValue("Staff")
public class Staff extends Person {

    public Staff() {
    }

    public Staff(String personalIdentificationNumber, String name, String surname) {
        super(personalIdentificationNumber, name, surname);
    }

}