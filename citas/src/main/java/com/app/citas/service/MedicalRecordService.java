package com.app.citas.service;

import com.app.citas.dto.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDTO createMedicalRecord(MedicalRecordDTO dto);
    List<MedicalRecordDTO> listMedicalRecords();
    MedicalRecordDTO getById(Long id);
    List<MedicalRecordDTO> findByAppointmentId(Long appointmentId);
    MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordDTO dto);
    void deleteMedicalRecord(Long id);
}
