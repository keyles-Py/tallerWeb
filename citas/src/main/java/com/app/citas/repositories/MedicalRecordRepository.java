package com.app.citas.repositories;


import com.app.citas.model.Appointment;
import com.app.citas.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
    MedicalRecord findMedicalRecordById(String fullName);
    List<MedicalRecord> findMedicalRecordByAppointment(Appointment appointment);

}
