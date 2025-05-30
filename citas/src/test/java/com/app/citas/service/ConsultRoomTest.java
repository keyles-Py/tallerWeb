package com.app.citas.service;

import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.mapper.ConsultRoomMapper;
import com.app.citas.model.ConsultRoom;
import com.app.citas.repositories.ConsultRoomRepository;
import com.app.citas.service.impl.ConsultRoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultRoomTest {
    @Mock
    private ConsultRoomRepository consultRoomRepository;
    @Mock
    private ConsultRoomMapper consultRoomMapper;

    @InjectMocks
    private ConsultRoomServiceImpl consultRoomService;

    @Test
    void shouldCreateConsultRoom() {
        ConsultRoom consultRoom = ConsultRoom.builder().id(1L).name("Consultorio #4").build();
        ConsultRoomDTO dto = ConsultRoomDTO.builder().id(1l).name("Consultorio #4").build();

        when(consultRoomMapper.toEntity(dto)).thenReturn(consultRoom);
        when(consultRoomRepository.save(consultRoom)).thenReturn(consultRoom);
        when(consultRoomMapper.toDTO(consultRoom)).thenReturn(dto);

        ConsultRoomDTO result = consultRoomService.createConsultRoom(dto);

        assertEquals("Consultorio #4", result.getName());
        verify(consultRoomRepository).save(consultRoom);
    }

    @Test
    void shouldUpdateConsultRoom() {
        ConsultRoom consultRoom = ConsultRoom.builder().id(1L).name("Consultorio #4").build();
        ConsultRoomDTO dto = ConsultRoomDTO.builder().name("Consultorio #5").build();

        when(consultRoomRepository.findConsultRoomById(1l)).thenReturn(Optional.of(consultRoom));
        when(consultRoomRepository.save(any())).thenReturn(consultRoom);
        when(consultRoomMapper.toDTO(any())).thenReturn(dto);

        ConsultRoomDTO result = consultRoomService.updateConsultRoom(1l, dto);

        assertEquals("Consultorio #5", result.getName());
    }

    @Test
    void shouldGetConsultRoomByName(){
        ConsultRoom consultRoom = ConsultRoom.builder().id(1L).name("Consultorio #4").build();
        ConsultRoomDTO dto = ConsultRoomDTO.builder().id(1L).name("Consultorio #4").build();

        when(consultRoomRepository.findConsultRoomById(1L)).thenReturn(Optional.of(consultRoom));
        when(consultRoomMapper.toDTO(consultRoom)).thenReturn(dto);

        ConsultRoomDTO result = consultRoomService.findById(1l);

        assertEquals("Consultorio #4", result.getName());
    }
}
