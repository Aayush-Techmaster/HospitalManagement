package com.learning.HospitalManagement.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int DId;
    private String FirstName;
    private String LastName;
    private String specialisation;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments66;
}
