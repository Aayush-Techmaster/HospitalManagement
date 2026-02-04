package com.learning.HospitalManagement.Service;

import com.learning.HospitalManagement.DTO.*;
import com.learning.HospitalManagement.Repository.*;
import com.learning.HospitalManagement.model.*;
import jakarta.transaction.Transactional;
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

    public List<PatientResponse> getAllPatient(){
    List<Patient>patients=patientRepo.findAll();

    return patients.stream()
            .map(p->new PatientResponse(p.getPid(),p.getFirstName(),
                    p.getLastName(),p.getAge()
            ))
            .toList();

    }

    public List<DoctorResponse> getAllDoctor(){

        List<Doctor> doctors = doctorRepo.findAll();

        return doctors.stream()
                .map(d->new DoctorResponse(
                        d.getDId(),
                        d.getFirstName(),
                        d.getLastName(),
                        d.getSpecialisation()
                ))
                .toList();
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

    @Transactional
    public String deletePatient(int Pid) {
        Patient patient = patientRepo.findById(Pid)
                .orElseThrow(()->new RuntimeException("Patient not found"));

        List<Appointment> appointments = appRepo.findByPatientPid(Pid);

        for (Appointment ap : appointments) {
            ap.setPatient(null);

        }
        appRepo.saveAll(appointments);

        patient.getMedicines().clear();
        patientRepo.save(patient);

        MedicalRecord record = patient.getMedicalRecord();

        patient.setMedicalRecord(null);
        patientRepo.save(patient);

        medicalRepo.delete(record);

        patientRepo.delete(patient);
        return "Successfully removed the patient from DB";
        }

    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment>app = appRepo.findAll();
        return app.stream()
                .map(a->new AppointmentResponse(
                        a.getId(),
                        a.getPatient().getFirstName(),
                        a.getDoctor().getFirstName()
                ))
                .toList();
    }


    public List<MedResponse> getAllMed() {
        List<Medicine> med = medicineRepo.findAll();
        return med.stream()
                .map(m->new MedResponse(
                        m.getId(),
                        m.getName(),
                        m.getType(),
                        m.getCompany()
                ))
                .toList();
    }
}

