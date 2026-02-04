package com.learning.HospitalManagement.DTO;


import lombok.Data;

@Data
public class DoctorResponse {
    private int DId;
    private String FirstName;
    private String LastName;
    private String specialisation;

    public DoctorResponse(int DId, String firstName, String lastName, String specialisation) {
        this.DId = DId;
        FirstName = firstName;
        LastName = lastName;
        this.specialisation = specialisation;
    }
}
