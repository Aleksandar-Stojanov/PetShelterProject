package com.example.demo.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "adopter_adopts_pet")
public class Adopter_adopts_pet {

    @EmbeddedId
    private AdopterAdoptsPetId id;

//    @ManyToOne
//    @MapsId("personalIdentificationNumber")
//    @JoinColumn(name = "personal_identification_number", referencedColumnName = "personal_identification_number")
//    private Adopter adopter;
//
//    @ManyToOne
//    @MapsId("idPet")
//    @JoinColumn(name = "id_pet", referencedColumnName = "id_pet")
//    private Pet pet;

    private Date adoption_date;

    public Adopter_adopts_pet() {

    }

    public void setAdoption_date(Date adoption_date) {
        this.adoption_date = adoption_date;
    }

    public Adopter_adopts_pet(AdopterAdoptsPetId aap) {
        this.id=aap;
    }


}
