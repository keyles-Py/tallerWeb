package com.app.citas.repositories;

import com.app.citas.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByFullName(String fullName);
    Doctor findByEmail(String email);
    Set<Doctor> findBySpecialty(String specialty);
    Optional<Doctor> findDoctorById(Long id);
    List<Doctor> findDoctorByAvailableFrom(LocalTime availableFrom);
    List<Doctor> findDoctorByAvailableTo(LocalTime availableTo);
}
