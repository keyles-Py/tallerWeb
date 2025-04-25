package com.app.citas.controller;

import com.app.citas.dto.DoctorDTO;
import com.app.citas.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorDTO));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> listDoctors(){
        return ResponseEntity.ok(doctorService.listDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO>  updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO){
        return ResponseEntity.ok(doctorService.updateDoctor(id,doctorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO>  deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
