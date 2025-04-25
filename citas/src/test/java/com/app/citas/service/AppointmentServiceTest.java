package com.app.citas.service;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.mapper.AppointmentMapper;
import com.app.citas.model.Appointment;
import com.app.citas.repositories.AppointmentRepository;
import com.app.citas.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.support.ClassicRequestBuilder.post;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;

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
    /*
    @Test
    void shouldUpdateAppointment() {
        Appointment appointment = Appointment.builder().id(1L).build();
        AppointmentDTO appointmentDTO = AppointmentDTO.builder().id(2L).build();

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any())).thenReturn(appointment);
        when(appointmentMapper.toDTO(any())).thenReturn(appointmentDTO);

        AppointmentDTO result = appointmentService.updateAppointment(1L, appointmentDTO);

        assertEquals(2L, result.getId());

    }

     */

    @Test
    void shouldCreateAppointment() {
        AppointmentDTO dto = AppointmentDTO.builder().id(1L).status(AppointmentDTO.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();
        Appointment appointment = Appointment.builder().id(1L).status(Appointment.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();

        when(appointmentMapper.toEntity(dto)).thenReturn(appointment);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.toDTO(appointment)).thenReturn(dto);

        AppointmentDTO result = appointmentService.createAppointment(dto);

        assertEquals(AppointmentDTO.Status.SCHEDULED, result.getStatus());
        verify(appointmentRepository).save(appointment);

    }
}
