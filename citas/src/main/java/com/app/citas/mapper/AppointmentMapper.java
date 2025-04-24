package com.app.citas.mapper;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AppointmentMapper {
    AppointmentDTO toDTO(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);
}
