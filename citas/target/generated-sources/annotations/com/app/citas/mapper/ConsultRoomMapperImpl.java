package com.app.citas.mapper;

import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.model.ConsultRoom;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T00:31:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ConsultRoomMapperImpl implements ConsultRoomMapper {

    @Override
    public ConsultRoomDTO toDTO(ConsultRoom consultRoom) {
        if ( consultRoom == null ) {
            return null;
        }

        ConsultRoomDTO.ConsultRoomDTOBuilder consultRoomDTO = ConsultRoomDTO.builder();

        consultRoomDTO.name( consultRoom.getName() );
        consultRoomDTO.floor( consultRoom.getFloor() );
        consultRoomDTO.description( consultRoom.getDescription() );

        return consultRoomDTO.build();
    }

    @Override
    public ConsultRoom toEntity(ConsultRoomDTO consultRoomDTO) {
        if ( consultRoomDTO == null ) {
            return null;
        }

        ConsultRoom consultRoom = new ConsultRoom();

        consultRoom.setName( consultRoomDTO.getName() );
        consultRoom.setFloor( consultRoomDTO.getFloor() );
        consultRoom.setDescription( consultRoomDTO.getDescription() );

        return consultRoom;
    }
}
