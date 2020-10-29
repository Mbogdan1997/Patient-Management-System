package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.PatientDTO;

import com.utcn.hospital.entity.Patient;
import com.utcn.hospital.repository.PatientRepository;

@Component
public class PatientMapper implements AbstractMapper<Patient,PatientDTO>,ListMapper<Patient, PatientDTO> {

	private PatientRepository patientRepository;
	
	@Autowired
	public PatientMapper(PatientRepository patientRepository) {
		
		this.patientRepository=patientRepository;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Optional<Patient> toEntityUpdate(PatientDTO dto) {
		// TODO Auto-generated method stub
		return patientRepository.findById(dto.getId());
	}

	@Override
	public Patient toEntityInsert(PatientDTO dto) {
		// TODO Auto-generated method stub
		return new Patient(dto);
	}

	@Override
	public PatientDTO toDTO(Patient entity) {
		// TODO Auto-generated method stub
		return new PatientDTO(entity);
	}


	@Override
	public List<PatientDTO> toDTOs(List<Patient> entities) {
		// TODO Auto-generated method stub
		List<PatientDTO> patientDTOs=new ArrayList<PatientDTO>();
		for(Patient entity:entities) {
			patientDTOs.add(this.toDTO(entity));
		}
		
		return patientDTOs;
	}

	@Override
	public List<Patient> toEntities(List<PatientDTO> dtos) {
		// TODO Auto-generated method stub
		List<Patient> patients=new ArrayList<Patient>();
		for(PatientDTO dto:dtos) {
			patients.add(this.toEntityUpdate(dto).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}
		
		return patients;
	}

}
