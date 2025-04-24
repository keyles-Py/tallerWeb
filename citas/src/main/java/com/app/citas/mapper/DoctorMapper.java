package com.app.citas.mapper;

import com.app.citas.dto.DoctorDTO;
import com.app.citas.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface DoctorMapper {
    DoctorDTO toDto(Doctor doctor);
    Doctor toEntity(DoctorDTO doctorDTO);
}
