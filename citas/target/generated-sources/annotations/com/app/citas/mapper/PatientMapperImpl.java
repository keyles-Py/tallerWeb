package com.app.citas.mapper;

import com.app.citas.dto.PatientDTO;
import com.app.citas.model.Patient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T00:31:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient toPatient(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setFullName( patientDTO.getFullName() );
        patient.setEmail( patientDTO.getEmail() );
        patient.setPhone( patientDTO.getPhone() );

        return patient;
    }

    @Override
    public PatientDTO toPatientDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO.PatientDTOBuilder patientDTO = PatientDTO.builder();

        patientDTO.fullName( patient.getFullName() );
        patientDTO.email( patient.getEmail() );
        patientDTO.phone( patient.getPhone() );

        return patientDTO.build();
    }
}
