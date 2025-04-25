package com.app.citas.repositories;

import com.app.citas.model.Appointment;
import com.app.citas.model.ConsultRoom;
import com.app.citas.model.Doctor;
import com.app.citas.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findAppointmentById(Long id);
    Appointment findAppointmentsByPatient(Patient patient);
    List<Appointment> findAppointmentsByDoctor(Doctor doctor);
    List<Appointment> findAppointmentsByPatientAndDoctor(Patient patient, Doctor doctor);
    List<Appointment> findAppointmentByConsultRoom(ConsultRoom consultRoom);
}
