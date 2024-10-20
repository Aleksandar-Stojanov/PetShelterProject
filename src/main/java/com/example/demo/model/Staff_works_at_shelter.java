package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff_works_at_shelter")
public class Staff_works_at_shelter {
    @EmbeddedId
    private Staff_works_at_shelter_id staffWorksAtShelterId;

}
