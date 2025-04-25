package com.app.citas.service;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.model.Patient;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> getAllApoAppointment(Long id);
    AppointmentDTO getAppointment(Long id);
    AppointmentDTO getAppointmentsByPatient(Patient patient);
    List<AppointmentDTO> getAppointmentByConsultRoom(ConsultRoomDTO consultRoomDTO);
    void deleteAppointment(Long id);
}
