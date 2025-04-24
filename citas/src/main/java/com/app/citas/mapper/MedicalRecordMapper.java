package com.app.citas.mapper;

import com.app.citas.dto.MedicalRecordDTO;
import com.app.citas.model.MedicalRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MedicalRecordMapper {
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);
}
