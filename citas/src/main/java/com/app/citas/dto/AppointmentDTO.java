package com.app.citas.dto;

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

    private PatientDTO patient;
    private DoctorDTO doctor;
    private ConsultRoomDTO  consultRoom;
    @Future
    private LocalDateTime startTime;
    @Future
    private LocalDateTime endTime;
    private Status status;

    public enum Status {
        SCHEDULED, COMPLETED, CANCELED
    }
}
