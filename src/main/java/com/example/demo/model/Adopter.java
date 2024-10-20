package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@PrimaryKeyJoinColumn(name = "personal_identification_number")
@Table(name = "adopter")
@DiscriminatorValue("Adopter")
public class Adopter extends Person {

    public Adopter() {
    }

    public Adopter(String personal_identification_number, String name, String surname) {
        super(personal_identification_number, name, surname);
    }


}
