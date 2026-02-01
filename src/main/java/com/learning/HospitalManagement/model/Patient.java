package com.learning.HospitalManagement.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Pid;
    private String FirstName;
    private String LastName;
    private int age;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;


}
