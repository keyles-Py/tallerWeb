package com.app.citas.repositories;

import com.app.citas.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    Patient findById(long id);
    Patient findByFullName(String fullName);
    Patient findPatientByPhone(String phone);
    Boolean existsById(long id);

}
