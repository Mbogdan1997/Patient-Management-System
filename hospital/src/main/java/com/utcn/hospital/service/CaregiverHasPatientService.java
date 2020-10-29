package com.utcn.hospital.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.CaregiverHasPatientDTO;
import com.utcn.hospital.dto.CaregiverHasPatientKeyDTO;
import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.entity.CaregiverHasPatientKey;
import com.utcn.hospital.mapper.CaregiverHasPatientMapper;
import com.utcn.hospital.repository.CaregiverHasPatientRepository;


@Service
public class CaregiverHasPatientService {
	
	private CaregiverHasPatientMapper caregiverHasPatientMapper;
	private CaregiverHasPatientRepository caregiverHasPatientRepository;
	
	@Autowired
	public CaregiverHasPatientService(CaregiverHasPatientMapper caregiverHasPatientMapper, CaregiverHasPatientRepository caregiverHasPatientRepository) {
		super();
		this.caregiverHasPatientMapper = caregiverHasPatientMapper;
		this.caregiverHasPatientRepository = caregiverHasPatientRepository;
	}
	
	public List<CaregiverHasPatientDTO> findCaregiverHasPatientById(int id) {
		List<CaregiverHasPatient> caregiverHasPatient=
				caregiverHasPatientRepository.findAll().stream()
				.filter(x->x.getCaregiver().getId()==id).collect(Collectors.toList());
		if(caregiverHasPatient!=null) {
			return caregiverHasPatientMapper.toDTOs(caregiverHasPatient);
		}
		return null;
	}

	public CaregiverHasPatientDTO insert(CaregiverHasPatientDTO userDTO) {
		// TODO Auto-generated method stub
		return caregiverHasPatientMapper.toDTO(caregiverHasPatientRepository.save(caregiverHasPatientMapper.toEntityInsert(userDTO)));
		
	}
	
	public List<CaregiverHasPatientDTO> viewAllAfterCaregiverId(int id ) {
		// TODO Auto-generated method stub
		return caregiverHasPatientMapper.toDTOs(caregiverHasPatientRepository.findByCaregiverId(id));
	}
	
	public List<CaregiverHasPatientDTO> viewAll() {
		// TODO Auto-generated method stub
		return caregiverHasPatientMapper.toDTOs(caregiverHasPatientRepository.findAll());
	}


	public void delete(CaregiverHasPatientDTO caregiverHasPatientDTO) {
		// TODO Auto-generated method stub
		
		CaregiverHasPatient caregiverHasPatient = caregiverHasPatientMapper.toEntityUpdate(caregiverHasPatientDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + caregiverHasPatientDTO.getId()));
		caregiverHasPatientRepository.delete(caregiverHasPatient);
		
	}
	
	public boolean check(int caregiverId,int patientId) {
		
		if(caregiverHasPatientRepository.findCaregiverHasPatient(caregiverId, patientId)!=null) {
			return true;
		}
		return false;
		
	}
	
	

}
