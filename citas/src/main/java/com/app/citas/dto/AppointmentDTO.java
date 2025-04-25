package com.app.citas.dto;

import com.app.citas.model.ConsultRoom;
import com.app.citas.model.Doctor;
import com.app.citas.model.Patient;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private ConsultRoom consultRoom;
    @Future
    private LocalDateTime startTime;
    @Future
    private LocalDateTime endTime;
    private Status status;

    public enum Status {
        SCHEDULED, COMPLETED, CANCELED
    }
}
