package com.app.citas.service.impl;

import com.app.citas.dto.MedicalRecordDTO;
import com.app.citas.mapper.MedicalRecordMapper;
import com.app.citas.model.Appointment;
import com.app.citas.model.MedicalRecord;
import com.app.citas.repositories.AppointmentRepository;
import com.app.citas.repositories.MedicalRecordRepository;
import com.app.citas.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO dto) {
        MedicalRecord entity = medicalRecordMapper.toEntity(dto);
        Long appointment_Id = dto.getAppointment().getId();


        if (dto.getAppointment() != null && dto.getAppointment().getId() != null) {
            Long appointmentId = dto.getAppointment().getId();
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

            List<MedicalRecord> existingRecords = medicalRecordRepository.findMedicalRecordByAppointment(appointment);
            if (!existingRecords.isEmpty()) {
                throw new RuntimeException("A medical record already exists");
            }

            entity.setAppointment(appointment);
        } else {
            throw new RuntimeException("Appointment information is required");
        }


        MedicalRecord saved = medicalRecordRepository.save(entity);
        return medicalRecordMapper.toDTO(saved);
    }

    @Override
    public List<MedicalRecordDTO> listMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(medicalRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO getById(Long id) {
        return medicalRecordRepository.findById(id)
                .map(medicalRecordMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));
    }

    @Override
    public List<MedicalRecordDTO> findByAppointmentId(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        return medicalRecordRepository.findMedicalRecordByAppointment(appointment).stream()
                .map(medicalRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordDTO dto) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id: " + id));

        record.setDiagnosis(dto.getDiagnosis());
        record.setNotes(dto.getNotes());
        record.setCreationAt(dto.getCreationAt());

        return medicalRecordMapper.toDTO(medicalRecordRepository.save(record));
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }
}
