package com.app.citas.service;

import com.app.citas.dto.PatientDTO;
import com.app.citas.mapper.PatientMapper;
import com.app.citas.model.Patient;
import com.app.citas.repositories.PatientRepository;
import com.app.citas.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientTest {
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void shouldGetPatientById() {
        Patient paciente = Patient.builder().id(1L).fullName("James Bond").build();
        PatientDTO pacienteDTO = PatientDTO.builder().id(1L).fullName("James Bond").build();

        when(patientRepository.findPatientById(1L)).thenReturn(Optional.of(paciente));
        when(patientMapper.toPatientDTO(paciente)).thenReturn(pacienteDTO);

        PatientDTO result = patientService.getPatientById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldCheckIfPatientExists() {
        Patient paciente = Patient.builder().id(1L).fullName("James Bond").build();
        PatientDTO pacienteDTO = PatientDTO.builder().id(1L).fullName("James Bond").build();

        when(patientRepository.findPatientById(1L)).thenReturn(Optional.of(paciente));
        when(patientMapper.toPatientDTO(paciente)).thenReturn(pacienteDTO);

        PatientDTO result = patientService.getPatientById(1L);

        assertEquals(1L, result.getId());
        assertEquals("James Bond", result.getFullName());
    }
}
