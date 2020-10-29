package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.CaregiverHasPatientDTO;
import com.utcn.hospital.dto.CaregiverHasPatientKeyDTO;
import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.entity.CaregiverHasPatientKey;
import com.utcn.hospital.repository.CaregiverHasPatientRepository;

@Component
public class CaregiverHasPatientMapper implements AbstractMapper<CaregiverHasPatient, CaregiverHasPatientDTO>,ListMapper<CaregiverHasPatient, CaregiverHasPatientDTO> {
	
	private CaregiverMapper caregiverMapper;
	private PatientMapper patientMapper;
	private CaregiverHasPatientRepository caregiverHasPatientRepository;
	
	@Autowired
	public CaregiverHasPatientMapper(CaregiverHasPatientRepository caregiverHasPatientRepository,CaregiverMapper caregiverMapper, PatientMapper patientMapper) {
		super();
		this.caregiverHasPatientRepository=caregiverHasPatientRepository;
		this.caregiverMapper = caregiverMapper;
		this.patientMapper = patientMapper;
	}
	

	@Override
	public Optional<CaregiverHasPatient> toEntityUpdate(CaregiverHasPatientDTO dto) {
		// TODO Auto-generated method stub
		return caregiverHasPatientRepository.findById(new CaregiverHasPatientKey(dto.getCaregiverDTO().getId(),dto.getPatientDTO().getId()));
	}

	@Override
	public CaregiverHasPatient toEntityInsert(CaregiverHasPatientDTO dto) {
		// TODO Auto-generated method stub
		return new CaregiverHasPatient(new CaregiverHasPatientKeyDTO(dto.getCaregiverDTO().getId(),dto.getPatientDTO().getId()), caregiverMapper.toEntityUpdate(dto.getCaregiverDTO()).get()
				, patientMapper.toEntityUpdate(dto.getPatientDTO()).get());
	}

	@Override
	public CaregiverHasPatientDTO toDTO(CaregiverHasPatient entity) {
		// TODO Auto-generated method stub
		return new CaregiverHasPatientDTO(entity);
	}
	
	@Override
	public List<CaregiverHasPatientDTO> toDTOs(List<CaregiverHasPatient> entities) {
		// TODO Auto-generated method stub
		List<CaregiverHasPatientDTO> caregiverHasPatientDTOs=new ArrayList<CaregiverHasPatientDTO>();
		for(CaregiverHasPatient entity:entities) {
			caregiverHasPatientDTOs.add(this.toDTO(entity));
		}
		
		return caregiverHasPatientDTOs;
	}

	@Override
	public List<CaregiverHasPatient> toEntities(List<CaregiverHasPatientDTO> dtos) {
		// TODO Auto-generated method stub
		List<CaregiverHasPatient> caregiverHasPatients=new ArrayList<CaregiverHasPatient>();
		for(CaregiverHasPatientDTO dto:dtos) {
			caregiverHasPatients.add(this.toEntityUpdate(dto).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}
		
		return caregiverHasPatients;
	}
	
	

}
