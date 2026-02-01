package com.learning.HospitalManagement.DTO;

import lombok.Data;

@Data
public class AppointmentRequest {
    private int appId;
    private int patientId;
    private int doctorId;
}
