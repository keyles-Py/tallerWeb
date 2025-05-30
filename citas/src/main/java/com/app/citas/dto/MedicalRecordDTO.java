package com.app.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MedicalRecordDTO {


    private AppointmentDTO appointment;
    private String diagnosis;
    private String notes;
    private LocalDateTime creationAt;
}
