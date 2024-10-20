package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
@Embeddable
public class Phone_number_shelter_id implements Serializable {
//    @Column(nullable = false)
//    private Integer id_shelter;
    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private Shelter shelter;

    @Column(length = 30, nullable = false)
    private String shelter_phone_number;


}