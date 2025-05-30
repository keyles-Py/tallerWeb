package com.app.citas.mapper;

import com.app.citas.dto.DoctorDTO;
import com.app.citas.model.Doctor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T00:31:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO.DoctorDTOBuilder doctorDTO = DoctorDTO.builder();

        doctorDTO.fullName( doctor.getFullName() );
        doctorDTO.email( doctor.getEmail() );
        doctorDTO.availableFrom( doctor.getAvailableFrom() );
        doctorDTO.availableTo( doctor.getAvailableTo() );

        return doctorDTO.build();
    }

    @Override
    public Doctor toEntity(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setFullName( doctorDTO.getFullName() );
        doctor.setEmail( doctorDTO.getEmail() );
        doctor.setAvailableFrom( doctorDTO.getAvailableFrom() );
        doctor.setAvailableTo( doctorDTO.getAvailableTo() );

        return doctor;
    }
}
