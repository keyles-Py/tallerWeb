package com.app.citas.service.impl;

import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.mapper.ConsultRoomMapper;
import com.app.citas.model.ConsultRoom;
import com.app.citas.repositories.ConsultRoomRepository;
import com.app.citas.service.ConsultRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultRoomServiceImpl implements ConsultRoomService {

    private final ConsultRoomRepository consultRoomRepository;
    private final ConsultRoomMapper consultRoomMapper;

    @Override
    public ConsultRoomDTO createConsultRoom(ConsultRoomDTO dto) {
        ConsultRoom entity = consultRoomMapper.toEntity(dto);
        ConsultRoom saved = consultRoomRepository.save(entity);
        return consultRoomMapper.toDTO(saved);
    }

    @Override
    public List<ConsultRoomDTO> listConsultRooms() {
        return consultRoomRepository.findAll().stream()
                .map(consultRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultRoomDTO getById(Long id) {
        return consultRoomRepository.findById(id)
                .map(consultRoomMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("ConsultRoom not found with id: " + id));
    }

    @Override
    public List<ConsultRoomDTO> findByName(String name) {
        return consultRoomRepository.findConsultRoomByName(name).stream()
                .map(consultRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultRoomDTO> findByFloor(String floor) {
        return consultRoomRepository.findConsultRoomByFloor(floor).stream()
                .map(consultRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultRoomDTO> findByFloorAndName(String floor, String name) {
        ConsultRoom room = consultRoomRepository.findConsultRoomByFloorAndName(floor, name);
        if (room == null) return List.of();
        return List.of(consultRoomMapper.toDTO(room));
    }

    @Override
    public ConsultRoomDTO updateConsultRoom(Long id, ConsultRoomDTO dto) {
        ConsultRoom room = consultRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ConsultRoom not found"));

        room.setName(dto.getName());
        room.setFloor(dto.getFloor());
        room.setDescription(dto.getDescription());

        return consultRoomMapper.toDTO(consultRoomRepository.save(room));
    }

    @Override
    public void deleteConsultRoom(Long id) {
        if (!consultRoomRepository.existsById(id)) {
            throw new RuntimeException("ConsultRoom not found with id: " + id);
        }
        consultRoomRepository.deleteById(id);
    }
}
