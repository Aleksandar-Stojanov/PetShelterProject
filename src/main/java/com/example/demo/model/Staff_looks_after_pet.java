package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff_looks_after_pet")
public class Staff_looks_after_pet {

    @EmbeddedId
    private Staff_looks_after_pet_id staffLooksAfterPetId;

}
