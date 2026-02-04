package com.learning.HospitalManagement.DTO;

import lombok.Data;

@Data
public class MedResponse {
    private int id;
    private String name;
    private String type;
    private String company;

    public MedResponse(int id, String name, String type, String company) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
    }
}
