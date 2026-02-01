package com.learning.HospitalManagement.DTO;

import lombok.Data;


@Data
public class PatientRequest {

    private String FirstName;
    private String LastName;
    private int age;
    private int doctorId;


}
