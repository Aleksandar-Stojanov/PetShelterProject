package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shelter")
public class Shelter {
    public Shelter(){

    }
    @Id
    @GeneratedValue
    private int id_shelter;

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private String location_shelter;
    private int capacity;
}
