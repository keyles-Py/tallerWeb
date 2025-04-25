package com.app.citas.service.impl;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.mapper.AppointmentMapper;
import com.app.citas.model.Appointment;
import com.app.citas.model.ConsultRoom;
import com.app.citas.model.Doctor;
import com.app.citas.model.Patient;
import com.app.citas.repositories.AppointmentRepository;
import com.app.citas.repositories.ConsultRoomRepository;
import com.app.citas.repositories.DoctorRepository;
import com.app.citas.repositories.PatientRepository;
import com.app.citas.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {


    private  final AppointmentRepository appointmentRepository;
    private  AppointmentMapper appointmentMapper;

    private  final PatientRepository patientRepository;

    private final   DoctorRepository doctorRepository;

    private  final  ConsultRoomRepository consultRoomRepository;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment appointment = appointmentMapper.toEntity(dto);

        if (dto.getPatient() != null && dto.getPatient().getId() != null) {
            Patient patient = patientRepository.findById(dto.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            appointment.setPatient(patient);
        }

        if (dto.getDoctor() != null && dto.getDoctor().getId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            appointment.setDoctor(doctor);
        }

        if (dto.getConsultRoom() != null && dto.getConsultRoom().getId() != null) {
            ConsultRoom room = consultRoomRepository.findById(dto.getConsultRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Consult room not found"));
            appointment.setConsultRoom(room);
        }

        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(saved);
    }

    @Override
    public List<AppointmentDTO> getAllApoAppointment(Long id) {
        return List.of();
    }

    public List<AppointmentDTO> getAllAppointments(Long id) {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public AppointmentDTO getAppointmentsByPatient(Patient patient) {
        Appointment appointment = appointmentRepository.findAppointmentsByPatient(patient);
        if (appointment == null) {
            throw new RuntimeException("No appointment found for the patient");
        }
        return appointmentMapper.toDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentByConsultRoom(ConsultRoomDTO consultRoomDTO) {
        ConsultRoom room = consultRoomRepository.findById(consultRoomDTO.getId())
                .orElseThrow(() -> new RuntimeException("Consult room not found"));

        return appointmentRepository.findAppointmentByConsultRoom(room).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
