package com.learning.HospitalManagement.model;


import com.learning.HospitalManagement.Repository.MedicineRepo;
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

    @OneToOne
    @JoinColumn(name="medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToMany
    @JoinTable(name="med_patient_record",joinColumns =@JoinColumn(name="patient_id"),
            inverseJoinColumns = @JoinColumn(name="medicine_id"))
    private List<Medicine> medicines;

}
