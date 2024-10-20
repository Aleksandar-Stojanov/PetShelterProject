package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_number_shelter")
public class Phone_number_shelter {

    @EmbeddedId
    private Phone_number_shelter_id phone_number_shelter;

//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "id_shelter")
//    private Shelter shelter;
//
//    @Column(length = 30)
//    private String shelter_phone_number;


}
//TODO: NE MI E NAPRAVENO KOMPOZITEN KLUC KAKO SO TREBA NA SITE 4
