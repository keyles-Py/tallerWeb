package com.app.citas.service;

import com.app.citas.dto.ConsultRoomDTO;

import java.util.List;

public interface ConsultRoomService {
    ConsultRoomDTO createConsultRoom(ConsultRoomDTO dto);
    List<ConsultRoomDTO> listConsultRooms();
    ConsultRoomDTO findById(Long id);
    List<ConsultRoomDTO> findByName(String name);
    List<ConsultRoomDTO> findByFloor(String floor);
    List<ConsultRoomDTO> findByFloorAndName(String floor, String name);
    ConsultRoomDTO updateConsultRoom(Long id, ConsultRoomDTO dto);
    void deleteConsultRoom(Long id);
}
