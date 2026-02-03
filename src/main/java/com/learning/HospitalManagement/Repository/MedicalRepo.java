package com.learning.HospitalManagement.Repository;

import com.learning.HospitalManagement.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRepo extends JpaRepository<MedicalRecord,Integer> {
}
