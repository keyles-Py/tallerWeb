package com.app.citas.service;

import com.app.citas.dto.DoctorDTO;

import java.time.LocalTime;
import java.util.List;

public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> listDoctors();
    DoctorDTO getDoctorById(Long id);
    DoctorDTO getDoctorByEmail(String email);
    List<DoctorDTO> findBySpecialty(String specialty);
    List<DoctorDTO> findByAvailableFrom(LocalTime from);
    List<DoctorDTO> findByAvailableTo(LocalTime to);
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}
