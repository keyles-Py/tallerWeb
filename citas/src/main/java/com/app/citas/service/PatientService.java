package com.app.citas.service;

import com.app.citas.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    List<PatientDTO> listPatients();
    PatientDTO getPatientById(Long id);
    PatientDTO updatePatient(Long id,PatientDTO patientDTO);
    void deletePatient(Long id);

}
