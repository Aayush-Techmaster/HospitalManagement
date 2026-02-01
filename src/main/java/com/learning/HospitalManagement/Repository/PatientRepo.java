package com.learning.HospitalManagement.Repository;

import com.learning.HospitalManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
