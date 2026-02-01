package com.learning.HospitalManagement.DTO;

import lombok.Data;

@Data
public class DoctorRequest {
    private int DId;
    private String FirstName;
    private String LastName;
    private String specialisation;
}
