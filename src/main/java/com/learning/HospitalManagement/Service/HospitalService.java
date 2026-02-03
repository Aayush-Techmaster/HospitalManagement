package com.learning.HospitalManagement.Service;

import com.learning.HospitalManagement.DTO.AppointmentRequest;
import com.learning.HospitalManagement.DTO.DoctorRequest;
import com.learning.HospitalManagement.DTO.PatientRequest;
import com.learning.HospitalManagement.Repository.*;
import com.learning.HospitalManagement.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final AppRepo appRepo;
    private final MedicalRepo medicalRepo;
    private final MedicineRepo medicineRepo;

    public HospitalService(DoctorRepo doctorRepo, PatientRepo patientRepo,
                           AppRepo appRepo, MedicalRepo medicalRepo,MedicineRepo medicineRepo){
        this.doctorRepo = doctorRepo;
        this.patientRepo=patientRepo;
        this.appRepo = appRepo;
        this.medicalRepo = medicalRepo;
        this.medicineRepo=medicineRepo;
    }

    public List<Patient> getAllPatient(){
        return patientRepo.findAll();
    }

    public List<Doctor> getAllDoctor(){
        return doctorRepo.findAll();
    }

    public String addPatient(PatientRequest request){

        Doctor doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setAge(request.getAge());
        Medicine med = medicineRepo.findById(request.getMedicineId())
                        .orElseThrow(()->new RuntimeException("Medicine not found"));
        patient.setMedicines(List.of(med));
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBloodReport(request.getBloodReport());
        medicalRecord.setPastDiseases(request.getPastDiseases());
        medicalRecord.setWeight(request.getWeight());
        medicalRepo.save(medicalRecord);
        patient.setMedicalRecord(medicalRecord);
        patientRepo.save(patient);
        return "Success";
    }
    public String addDoctor(DoctorRequest d){
        Doctor doctor = new Doctor();
        doctor.setFirstName(d.getFirstName());
        doctor.setLastName(d.getLastName());
        doctor.setSpecialisation(d.getSpecialisation());
        doctorRepo.save(doctor);
        return "Success";
    }

    public String createAppointment(AppointmentRequest appointment){
        Patient patient = patientRepo.findById(appointment.getPatientId())
                .orElseThrow(()->new RuntimeException("Patient Not Found"));
        Doctor doctor = doctorRepo.findById(appointment.getDoctorId())
                .orElseThrow(()->new RuntimeException("doctor Not Found"));

        Appointment appointment1 = new Appointment();
        appointment1.setPatient(patient);
        appointment1.setDoctor(doctor);
        appRepo.save(appointment1);

        return "Done with adding Appointment";
    }

    public String createMedicine(Medicine medicine){
        Medicine medicine1 = new Medicine();
        medicine1.setName(medicine.getName());
        medicine1.setType(medicine.getType());
        medicine1.setCompany(medicine.getCompany());
        medicineRepo.save(medicine1);
        return "Successfully saved the medicine";
    }


    public String deleteDoctor(int DId) {
        Doctor doc = doctorRepo.findById(DId)
                .orElseThrow(()->new RuntimeException("Doctor not found"));


        List<Appointment> appointments = appRepo.findByDoctorDId(DId);

        for(Appointment ap:appointments){
            ap.setDoctor(null);
        }

        appRepo.saveAll(appointments);
        doctorRepo.delete(doc);
        return "successfully deleted the doctor.";
    }
}
