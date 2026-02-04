package com.learning.HospitalManagement.DTO;


import lombok.Data;

@Data
public class PatientResponse {
    private int pid;
    private String FirstName;
    private String LastName;
    private int age;

    public PatientResponse(int pid, String firstName, String lastName, int age) {
        this.pid = pid;
        FirstName = firstName;
        LastName = lastName;
        this.age = age;
    }
}
