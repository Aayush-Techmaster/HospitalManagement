package com.learning.HospitalManagement.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
}
