package com.learning.HospitalManagement.Repository;

import com.learning.HospitalManagement.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine,Integer> {
}
