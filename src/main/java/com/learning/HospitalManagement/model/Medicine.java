package com.learning.HospitalManagement.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String company;

    @ManyToMany(mappedBy = "medicines")
    private List<Patient> patients;

}
