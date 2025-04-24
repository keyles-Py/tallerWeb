package com.app.citas.mapper;

import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.model.ConsultRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ConsultRoomMapper {
    ConsultRoomDTO toDTO(ConsultRoom consultRoom);
    ConsultRoom toEntity(ConsultRoomDTO consultRoomDTO);
}
