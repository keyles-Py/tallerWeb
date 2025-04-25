package com.app.citas.controller;

import com.app.citas.dto.ConsultRoomDTO;
import com.app.citas.service.ConsultRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class ConsultRoomController {
    private final ConsultRoomService consultRoomService;

    @PostMapping
    public ResponseEntity<ConsultRoomDTO> createUser(@Valid @RequestBody ConsultRoomDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultRoomService.createConsultRoom(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultRoomDTO>> listConsultRooms() {
        return ResponseEntity.ok(consultRoomService.listConsultRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultRoomDTO> getConsultRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(consultRoomService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultRoomDTO> updateConsultRoom(@PathVariable Long id, @Valid @RequestBody ConsultRoomDTO dto) {
        return ResponseEntity.ok(consultRoomService.updateConsultRoom(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultRoom(@PathVariable Long id) {
        consultRoomService.deleteConsultRoom(id);
        return ResponseEntity.noContent().build();
    }
}
