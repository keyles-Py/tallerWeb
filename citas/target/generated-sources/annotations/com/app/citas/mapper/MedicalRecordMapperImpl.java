package com.app.citas.mapper;

import com.app.citas.dto.MedicalRecordDTO;
import com.app.citas.model.MedicalRecord;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T10:57:33-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class MedicalRecordMapperImpl implements MedicalRecordMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public MedicalRecordDTO toDTO(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }

        MedicalRecordDTO.MedicalRecordDTOBuilder medicalRecordDTO = MedicalRecordDTO.builder();

        medicalRecordDTO.id( medicalRecord.getId() );
        medicalRecordDTO.appointment( appointmentMapper.toDTO( medicalRecord.getAppointment() ) );
        medicalRecordDTO.diagnosis( medicalRecord.getDiagnosis() );
        medicalRecordDTO.notes( medicalRecord.getNotes() );
        medicalRecordDTO.creationAt( medicalRecord.getCreationAt() );

        return medicalRecordDTO.build();
    }

    @Override
    public MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO) {
        if ( medicalRecordDTO == null ) {
            return null;
        }

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setId( medicalRecordDTO.getId() );
        medicalRecord.setAppointment( appointmentMapper.toEntity( medicalRecordDTO.getAppointment() ) );
        medicalRecord.setDiagnosis( medicalRecordDTO.getDiagnosis() );
        medicalRecord.setNotes( medicalRecordDTO.getNotes() );
        medicalRecord.setCreationAt( medicalRecordDTO.getCreationAt() );

        return medicalRecord;
    }
}
