package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pet_lives_at_shelter")
public class Pet_lives_at_shelter {
    public Pet_lives_at_shelter(Pet_lives_at_shelter_Id id) {
        this.id = id;
    }

    public Pet_lives_at_shelter(Pet_lives_at_shelter_Id id, Date date_from) {
        this.id = id;
        this.date_from = date_from;
    }

    @EmbeddedId
    private Pet_lives_at_shelter_Id id;

//    @ManyToOne
//    @MapsId("id_pet")
//    @JoinColumn(name = "id_pet", referencedColumnName = "id_pet")
//    private Pet pet;
//
//    @ManyToOne
//    @MapsId("id_shelter")
//    @JoinColumn(name = "id_shelter", referencedColumnName = "id_shelter")
//    private Shelter shelter;
    @Column(nullable = false)
    private Date date_from;
    private Date date_to;

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
}
