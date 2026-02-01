package com.learning.HospitalManagement.Repository;

import com.learning.HospitalManagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepo extends JpaRepository<Appointment,Integer> {

}
