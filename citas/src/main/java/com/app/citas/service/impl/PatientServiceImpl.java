package com.app.citas.service.impl;

import com.app.citas.dto.PatientDTO;
import com.app.citas.mapper.PatientMapper;
import com.app.citas.model.Patient;
import com.app.citas.repositories.PatientRepository;
import com.app.citas.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toPatient(patientDTO);
        return patientMapper.toPatientDTO(patientRepository.save(patient));
    }

    @Override
    public List<PatientDTO> listPatients(){
        return patientRepository.findAll().stream().map(patientMapper::toPatientDTO).toList();
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toPatientDTO)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        return patientRepository.findById(id).map(patientInDB -> {
            patientInDB.setFullName(patientDTO.getFullName());
            patientInDB.setEmail(patientDTO.getEmail());
            patientInDB.setPhone(patientDTO.getPhone());
            Patient updatedPatient = patientRepository.save(patientInDB);
            return patientMapper.toPatientDTO(updatedPatient);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    @Override
    public void deletePatient(Long id){
        if (!patientRepository.existsById(id)){
           return;
        }
        patientRepository.deleteById(id);
    }

}
