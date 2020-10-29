package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.CaregiverDTO;

import com.utcn.hospital.entity.Caregiver;
import com.utcn.hospital.repository.CaregiverRepository;


@Component
public class CaregiverMapper implements AbstractMapper<Caregiver,CaregiverDTO>,ListMapper<Caregiver,CaregiverDTO> {
	
	
	private CaregiverRepository caregiverRepository;
	
	@Autowired
    public CaregiverMapper(CaregiverRepository caregiverRepository) {
		// TODO Auto-generated constructor stub
		
		this.caregiverRepository=caregiverRepository;
	}

	@Override
	public Optional<Caregiver> toEntityUpdate(CaregiverDTO dto) {
		return caregiverRepository.findById(dto.getId());
	}

	@Override
	public Caregiver toEntityInsert(CaregiverDTO dto) {
		// TODO Auto-generated method stub
		return new Caregiver(dto);
	}

	@Override
	public CaregiverDTO toDTO(Caregiver entity) {
		// TODO Auto-generated method stub
		return new CaregiverDTO(entity);
	}

	@Override
	public List<CaregiverDTO> toDTOs(List<Caregiver> entities) {
		// TODO Auto-generated method stub
		List<CaregiverDTO> caregiverDTOs=new ArrayList<CaregiverDTO>();
		for(Caregiver entity:entities) {
			caregiverDTOs.add(this.toDTO(entity));
		}
		
		return caregiverDTOs;
	}

	@Override
	public List<Caregiver> toEntities(List<CaregiverDTO> dtos) {
		// TODO Auto-generated method stub
		List<Caregiver> caregivers=new ArrayList<Caregiver>();
		for(CaregiverDTO dto:dtos) {
			caregivers.add(this.toEntityUpdate(dto).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}
		
		return caregivers;
	}


}
