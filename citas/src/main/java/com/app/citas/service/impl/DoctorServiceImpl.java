package com.app.citas.service.impl;

import com.app.citas.dto.DoctorDTO;
import com.app.citas.mapper.DoctorMapper;
import com.app.citas.model.Doctor;
import com.app.citas.repositories.DoctorRepository;
import com.app.citas.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Override
    public List<DoctorDTO> listDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO findDoctorById(Long id) {
        return doctorRepository.findDoctorById(id)
                .map(doctorMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found with email: " + email);
        }
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDTO> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty).stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findByAvailableFrom(LocalTime from) {
        return doctorRepository.findDoctorByAvailableFrom(from).stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findByAvailableTo(LocalTime to) {
        return doctorRepository.findDoctorByAvailableTo(to).stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setFullName(dto.getFullName());
        doctor.setEmail(dto.getEmail());;
        doctor.setAvailableFrom(dto.getAvailableFrom());
        doctor.setAvailableTo(dto.getAvailableTo());

        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}