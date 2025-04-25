package com.app.citas.service.impl;

import com.app.citas.dto.PatientDTO;
import com.app.citas.mapper.PatientMapper;
import com.app.citas.model.Patient;
import com.app.citas.service.PatientService;
import com.github.dockerjava.api.model.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO createPatient(PatientDTO dto){
        Patient patient = patientMapper.toPatient(dto);
        return patientMapper.toPatientDTO(patientRepository.save(patient));
    }

    @Override
    public List<PatientDTO> listPatients(){
        return patientRepository.findAll().stream().map(patientMapper::toPatientDTO).toList();
    }

    @Override
    public PatientDTO getPatientById(Long id){
        return patientRepository.findById(id).map(patientMapper::toPatientDTO);//.orElseThrow(()-> new )
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        return patientRepository.findById(id).map(patientinDB -> {patientinDB.setFullName(patientDTO.getFullName());
        patientinDB.setEmail(patientDTO.getEmail());
        patientinDB.setPhone(patientDTO.getPhone());
        return patientMapper.toPatientDTO(patientRepository.save(patientinDB));
        });//orElseThrow
    }
    @Override
    public void deletePatient(Long id){
        if (!patientRepository.existById(id)){
           return;
        }
        patientRepository.deleteById(id);
    }
}
