package com.app.citas.service;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.mapper.AppointmentMapper;
import com.app.citas.model.Appointment;
import com.app.citas.model.Doctor;
import com.app.citas.repositories.AppointmentRepository;
import com.app.citas.repositories.DoctorRepository;
import com.app.citas.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldGetAppointmentById() {
        Appointment appointment = Appointment.builder().id(1L).build();
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().id(1L).build();

        when(appointmentRepository.findAppointmentById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentMapper.toDTO(appointment)).thenReturn(appointmentDTO);

        AppointmentDTO result = appointmentService.getAppointment(1L);

        assertEquals(1l, result.getId());
    }

    @Test
    void shouldUpdateAppointment() {
        Doctor doctor1 = Doctor.builder().id(1L).fullName("Carlo Magno").build();
        Doctor doctor2 = Doctor.builder().id(2L).fullName("Michael Jackson").build();
        Appointment appointment = Appointment.builder().id(1L).doctor(doctor1).build();
        AppointmentDTO dto = AppointmentDTO.builder().id(2L).doctor(doctor2).build();

        when(appointmentRepository.findAppointmentById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any())).thenReturn(appointment);
        when(appointmentMapper.toDTO(any())).thenReturn(dto);

        AppointmentDTO result = appointmentService.updateAppointment(1L, dto);

        assertEquals(doctor2, result.getDoctor());
    }

    @Test
    void shouldCreateAppointment() {
        Doctor doctor = Doctor.builder().id(1L).availableFrom(LocalTime.of(8, 0)).availableTo(LocalTime.of(23, 0)).fullName("Jack the Reaper").build();
        AppointmentDTO dto = AppointmentDTO.builder().id(1L).doctor(doctor).status(AppointmentDTO.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();
        Appointment appointment = Appointment.builder().id(1L).doctor(doctor).status(Appointment.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();

        when(doctorRepository.findDoctorById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentMapper.toEntity(dto)).thenReturn(appointment);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.toDTO(appointment)).thenReturn(dto);

        AppointmentDTO result = appointmentService.createAppointment(dto);

        assertEquals(AppointmentDTO.Status.SCHEDULED, result.getStatus());
        verify(appointmentRepository).save(appointment);

    }
}
