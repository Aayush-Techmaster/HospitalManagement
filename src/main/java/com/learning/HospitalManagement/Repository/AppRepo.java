package com.learning.HospitalManagement.Repository;

import com.learning.HospitalManagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppRepo extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByDoctorDId(int DId);
}
