package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_number_person")
public class Phone_number_person {

    @EmbeddedId
    private Phone_number_person_id phone_number_person_id;


}
