package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {
    public Pet(String name, char gender, String species, String adoption_status, String breed, Date date_of_birth){
    this.name=name;
    this.gender=gender;
    this.species=species;
    this.adoption_status=adoption_status;
    this.breed=breed;
    this.date_of_birth=date_of_birth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pet;

    @Column(length = 20)
    private String name;

    @Column(nullable = false)
    private char gender;

    @Column(length = 20, nullable = false)
    private String species;

    @Column(length = 20, nullable = false)
    private String adoption_status;

    @Column(length = 20)
    private String breed;

    private Date date_of_birth;

    public Pet() {

    }
}
