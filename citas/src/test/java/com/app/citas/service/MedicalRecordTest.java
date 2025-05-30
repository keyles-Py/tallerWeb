package com.app.citas.service;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.dto.MedicalRecordDTO;
import com.app.citas.mapper.MedicalRecordMapper;
import com.app.citas.model.Appointment;
import com.app.citas.model.Doctor;
import com.app.citas.model.MedicalRecord;
import com.app.citas.repositories.AppointmentRepository;
import com.app.citas.repositories.MedicalRecordRepository;
import com.app.citas.service.impl.MedicalRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordTest {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    @Test
    void shouldCreateMedicalRecord() {
        Appointment appointment = Appointment.builder().id(1L).status(Appointment.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();
        AppointmentDTO Adto = AppointmentDTO.builder().id(1L).status(AppointmentDTO.Status.SCHEDULED).startTime(LocalDateTime.now().plusDays(2)).endTime(LocalDateTime.now().plusDays(2).plusHours(2)).build();
        MedicalRecordDTO dto = MedicalRecordDTO.builder().id(1l).appointment(Adto).build();
        MedicalRecord medicalRecord = MedicalRecord.builder().id(1l).appointment(appointment).build();

        when(appointmentRepository.findAppointmentById(1L)).thenReturn(Optional.of(appointment));
        when(medicalRecordMapper.toEntity(dto)).thenReturn(medicalRecord);
        when(medicalRecordRepository.save(medicalRecord)).thenReturn(medicalRecord);
        when(medicalRecordMapper.toDTO(medicalRecord)).thenReturn(dto);
        when(medicalRecordRepository.findMedicalRecordByAppointment(appointment)).thenReturn(Collections.emptyList());

        MedicalRecordDTO result = medicalRecordService.createMedicalRecord(dto);

        assertEquals(1L, result.getId());
        verify(medicalRecordRepository).save(medicalRecord);
    }

    @Test
    void shouldListMedicalRecords() {
        MedicalRecord medicalRecord = MedicalRecord.builder().id(1L).build();
        MedicalRecordDTO dto = MedicalRecordDTO.builder().id(1L).build();

        when(medicalRecordRepository.findAll()).thenReturn(List.of(medicalRecord));
        when(medicalRecordMapper.toDTO(medicalRecord)).thenReturn(dto);

        List<MedicalRecordDTO> medicalRecords = medicalRecordService.listMedicalRecords();

        assertEquals(1, medicalRecords.size());
        assertEquals(1l, medicalRecords.get(0).getId());
    }
}
