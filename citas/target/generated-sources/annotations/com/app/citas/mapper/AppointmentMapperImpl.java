package com.app.citas.mapper;

import com.app.citas.dto.AppointmentDTO;
import com.app.citas.model.Appointment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T00:31:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO.AppointmentDTOBuilder appointmentDTO = AppointmentDTO.builder();

        appointmentDTO.id( appointment.getId() );
        appointmentDTO.patient( appointment.getPatient() );
        appointmentDTO.doctor( appointment.getDoctor() );
        appointmentDTO.consultRoom( appointment.getConsultRoom() );
        appointmentDTO.startTime( appointment.getStartTime() );
        appointmentDTO.endTime( appointment.getEndTime() );
        appointmentDTO.status( statusToStatus( appointment.getStatus() ) );

        return appointmentDTO.build();
    }

    @Override
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointment = Appointment.builder();

        appointment.id( appointmentDTO.getId() );
        appointment.patient( appointmentDTO.getPatient() );
        appointment.doctor( appointmentDTO.getDoctor() );
        appointment.consultRoom( appointmentDTO.getConsultRoom() );
        appointment.startTime( appointmentDTO.getStartTime() );
        appointment.endTime( appointmentDTO.getEndTime() );
        appointment.status( statusToStatus1( appointmentDTO.getStatus() ) );

        return appointment.build();
    }

    protected AppointmentDTO.Status statusToStatus(Appointment.Status status) {
        if ( status == null ) {
            return null;
        }

        AppointmentDTO.Status status1;

        switch ( status ) {
            case SCHEDULED: status1 = AppointmentDTO.Status.SCHEDULED;
            break;
            case COMPLETED: status1 = AppointmentDTO.Status.COMPLETED;
            break;
            case CANCELED: status1 = AppointmentDTO.Status.CANCELED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + status );
        }

        return status1;
    }

    protected Appointment.Status statusToStatus1(AppointmentDTO.Status status) {
        if ( status == null ) {
            return null;
        }

        Appointment.Status status1;

        switch ( status ) {
            case SCHEDULED: status1 = Appointment.Status.SCHEDULED;
            break;
            case COMPLETED: status1 = Appointment.Status.COMPLETED;
            break;
            case CANCELED: status1 = Appointment.Status.CANCELED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + status );
        }

        return status1;
    }
}
