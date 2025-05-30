package com.app.citas.service;


import com.app.citas.dto.DoctorDTO;
import com.app.citas.mapper.DoctorMapper;
import com.app.citas.model.Doctor;
import com.app.citas.repositories.DoctorRepository;
import com.app.citas.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorTest {
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Test
    void shouldFindDoctorByName() {
        Doctor doctor = Doctor.builder().id(1L).fullName("Pedro Páramo").build();
        DoctorDTO dto = DoctorDTO.builder().id(1L).fullName("Pedro Páramo").build();

        when(doctorRepository.findDoctorById(1l)).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDto(doctor)).thenReturn(dto);

        DoctorDTO result = doctorService.findDoctorById(1L);

        assertEquals("Pedro Páramo", result.getFullName());
    }

    @Test
    void shouldDeleteDoctorById() {
        when(doctorRepository.existsById(1L)).thenReturn(true);

        doctorService.deleteDoctor(1L);

        verify(doctorRepository).deleteById(1L);

    }
}
