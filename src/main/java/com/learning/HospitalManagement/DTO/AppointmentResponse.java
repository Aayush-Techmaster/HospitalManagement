package com.learning.HospitalManagement.DTO;


import lombok.Data;

@Data
public class AppointmentResponse {
    private int aid;
    private String patientName;
    private String doctorName;

    public AppointmentResponse(
            int aid,String patientName, String doctorName) {
        this.aid=aid;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }
}
