package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {
    public Integer getId_application() {
        return id_application;
    }

    public String getPersonal_identification_number_adopter() {
        return personal_identification_number_adopter;
    }

    public String getPersonal_identification_number_staff() {
        return personal_identification_number_staff;
    }

    public Pet getPet() {
        return pet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_application;

    @Column(length = 20, nullable = false)
    private String personal_identification_number_adopter;

    @Column(length = 20)
    private String personal_identification_number_staff;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet pet;


    public Application() {
    }

    public Application(String personal_identification_number_adopter, String personal_identification_number_staff, Pet pet) {
        this.personal_identification_number_adopter = personal_identification_number_adopter;
        this.personal_identification_number_staff = personal_identification_number_staff;
        this.pet = pet;
    }

}
