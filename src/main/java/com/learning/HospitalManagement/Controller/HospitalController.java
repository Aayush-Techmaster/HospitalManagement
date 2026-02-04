package com.learning.HospitalManagement.Controller;


import com.learning.HospitalManagement.DTO.*;
import com.learning.HospitalManagement.model.Appointment;
import com.learning.HospitalManagement.model.Doctor;
import com.learning.HospitalManagement.model.Medicine;
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
    public List<PatientResponse> getAllPatient(){
        return hospitalService.getAllPatient();
    }

    @GetMapping("/Doctor")
    public List<DoctorResponse> getAllDoctor(){
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

    @PostMapping("/addMed")
    public String createMedicine(@RequestBody Medicine medicine){
        return hospitalService.createMedicine(medicine);
    }

    @DeleteMapping("/deleteDoctor/{DId}")
    public String deleteDoctor(@PathVariable int DId){
        return hospitalService.deleteDoctor(DId);

    }
    @DeleteMapping("/deletePatient/{Pid}")
    public String deletePatient(@PathVariable int Pid){
        return hospitalService.deletePatient(Pid);
    }

    @GetMapping("/getAppointments")
    public List<AppointmentResponse> getAppointments(){
        return hospitalService.getAllAppointments();

    }

    @GetMapping("/getMed")
    public List<MedResponse> getMed(){
        return hospitalService.getAllMed();
    }

}
