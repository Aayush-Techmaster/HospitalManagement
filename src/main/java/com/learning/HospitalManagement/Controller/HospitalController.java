package com.learning.HospitalManagement.Controller;


import com.learning.HospitalManagement.DTO.AppointmentRequest;
import com.learning.HospitalManagement.DTO.DoctorRequest;
import com.learning.HospitalManagement.DTO.PatientRequest;
import com.learning.HospitalManagement.model.Appointment;
import com.learning.HospitalManagement.model.Doctor;
import com.learning.HospitalManagement.model.Patient;
import com.learning.HospitalManagement.Service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Hospital")
public class HospitalController {


    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/Patient")
    public List<Patient> getAllPatient(){
        return hospitalService.getAllPatient();
    }

    @GetMapping("/Doctor")
    public List<Doctor> getAllDoctor(){
        return hospitalService.getAllDoctor();
    }

    @PostMapping("/addPatient")
    public String addPatient(@RequestBody PatientRequest request) {
        return hospitalService.addPatient(request);
    }

    @PostMapping("/addDoctor")
    public String addDoctor(@RequestBody DoctorRequest request){

        return hospitalService.addDoctor(request);
    }

    @PostMapping("/addAppointment")
    public String createAppointment(@RequestBody AppointmentRequest appointment){
        return hospitalService.createAppointment(appointment);
    }

}
