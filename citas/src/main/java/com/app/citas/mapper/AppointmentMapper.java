package com.app.citas.mapper;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        PatientMapper.class,
        DoctorMapper.class,
        ConsultRoomMapper.class
})
public interface AppointmentMapper {
    AppointmentDTO toDTO(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);
}
