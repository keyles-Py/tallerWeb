package com.app.citas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordController> createMedicalRecord(@Valid @RequestBody MedicalRecordController dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.createMedicalRecord(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordController>> listMedicalRecords() {
        return ResponseEntity.ok(medicalRecordService.listMedicalRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordController> getMedicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordController> updateMedicalRecord(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
