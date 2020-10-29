package com.utcn.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.CaregiverDTO;
import com.utcn.hospital.entity.Caregiver;
import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.mapper.CaregiverMapper;
import com.utcn.hospital.repository.CaregiverHasPatientRepository;
import com.utcn.hospital.repository.CaregiverRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class CaregiverService {

	private CaregiverMapper caregiverMapper;
	private CaregiverRepository caregiverRepository;
	private CaregiverHasPatientRepository caregiverHasPatientRepository;

	@Autowired
	public CaregiverService(CaregiverMapper caregiverMapper, CaregiverRepository caregiverRepository,CaregiverHasPatientRepository caregiverHasPatientRepository) {
		super();
		this.caregiverMapper = caregiverMapper;
		this.caregiverRepository = caregiverRepository;
		this.caregiverHasPatientRepository=caregiverHasPatientRepository;
	}

	public CaregiverDTO insert(CaregiverDTO userDTO) {
		if (caregiverRepository.findByUsername(userDTO.getUsername()) == null) {
			return caregiverMapper.toDTO(caregiverRepository.save(caregiverMapper.toEntityInsert(userDTO)));
		}
		return null;

	}

	public CaregiverDTO findCaregiverById(int id) {
		Caregiver caregiver = caregiverRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (caregiver != null) {
			return caregiverMapper.toDTO(caregiver);
		}
		return null;
	}

	public List<CaregiverDTO> viewAll() {
		// TODO Auto-generated method stub
		return caregiverMapper.toDTOs(caregiverRepository.findAll());
	}

	public CaregiverDTO update(CaregiverDTO caregiverDTO) {
		// TODO Auto-generated method stub

		Caregiver caregiver = caregiverMapper.toEntityUpdate(caregiverDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + caregiverDTO.getId()));
		caregiver.update(caregiverDTO);
		caregiverRepository.save(caregiver);
		return caregiverMapper.toDTO(caregiver);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

		List<CaregiverHasPatient> caregiverHasPatients=caregiverHasPatientRepository.findByCaregiverId(id);
		Caregiver caregiver = caregiverRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		caregiverHasPatientRepository.deleteAll(caregiverHasPatients);
		caregiverRepository.delete(caregiver);

	}

}
