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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {


    private  final AppointmentRepository appointmentRepository;

    private  final AppointmentMapper appointmentMapper;

    private  final PatientRepository patientRepository;

    private final   DoctorRepository doctorRepository;

    private  final  ConsultRoomRepository consultRoomRepository;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment appointment = appointmentMapper.toEntity(dto);


        Patient patient;
        Doctor doctor = null;
        ConsultRoom room = null;
        LocalDateTime dateTime = dto.getStartTime();


        if (dto.getPatient() != null && dto.getPatient().getId() != null) {
            patient = patientRepository.findById(dto.getPatient().getId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            appointment.setPatient(patient);
        }


        if (dto.getDoctor() != null && dto.getDoctor().getId() != null) {
            doctor = doctorRepository.findDoctorById(dto.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            appointment.setDoctor(doctor);
        }


        if (dto.getConsultRoom() != null && dto.getConsultRoom().getId() != null) {
            room = consultRoomRepository.findById(dto.getConsultRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Consult room not found"));
            appointment.setConsultRoom(room);
        }


        if (doctor != null && dateTime != null &&
                appointmentRepository.existsByDoctorAndStartTime(doctor, dateTime)) {
            throw new RuntimeException("This doctor is already booked  " + dateTime);
        }

        if (dateTime != null) {
            LocalTime appointmentTime = dateTime.toLocalTime();
            if (appointmentTime.isBefore(doctor.getAvailableFrom()) || appointmentTime.isAfter(doctor.getAvailableTo())) {
                throw new RuntimeException("This appointment is outside of the doctor's available hours.");
            }
        }

        if (room != null && dateTime != null &&
                appointmentRepository.existsByConsultRoomAndStartTime(room, dateTime)) {
            throw new RuntimeException("This consult room is already booked " + dateTime);
        }

        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(saved);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointment(Long id) {
        return appointmentRepository.findAppointmentById(id)
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
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setDoctor(dto.getDoctor());
        appointment.setPatient(dto.getPatient());
        appointment.setConsultRoom(dto.getConsultRoom());

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
