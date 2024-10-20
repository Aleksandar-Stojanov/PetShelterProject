package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

    @Id
    @Column(length = 20)
    private String personal_identification_number;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30,nullable = false)
    private String surname;

    public Person() {
    }

    public Person(String personal_identification_number, String name, String surname) {
        this.personal_identification_number = personal_identification_number;
        this.name = name;
        this.surname = surname;
    }

}
