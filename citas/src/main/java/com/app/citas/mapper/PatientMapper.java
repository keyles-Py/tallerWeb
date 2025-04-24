package com.app.citas.mapper;

import com.app.citas.dto.PatientDTO;
import com.app.citas.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface PatientMapper {
    Patient toPatient(PatientDTO patientDTO);
    PatientDTO toPatientDTO(Patient patient);
}
