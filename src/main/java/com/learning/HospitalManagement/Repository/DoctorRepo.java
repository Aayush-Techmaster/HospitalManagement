package com.learning.HospitalManagement.Repository;


import com.learning.HospitalManagement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
    Integer DId(int dId);
}
