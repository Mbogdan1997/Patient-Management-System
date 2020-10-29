package com.utcn.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.PatientDTO;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.entity.Patient;
import com.utcn.hospital.mapper.PatientMapper;
import com.utcn.hospital.repository.CaregiverHasPatientRepository;
import com.utcn.hospital.repository.PatientRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class PatientService {

	private PatientMapper patientMapper;
	private PatientRepository patientRepository;
	private CaregiverHasPatientRepository caregiverHasPatientRepository;

	@Autowired
	public PatientService(PatientMapper patientMapper, PatientRepository patientRepository,CaregiverHasPatientRepository caregiverHasPatientRepository) {
		super();
		this.patientMapper = patientMapper;
		this.patientRepository = patientRepository;
		this.caregiverHasPatientRepository=caregiverHasPatientRepository;
	}

	public PatientDTO findPatientById(int id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (patient != null) {
			return patientMapper.toDTO(patient);
		}
		return null;
	}

	public PatientDTO insert(PatientDTO userDTO) {
		// TODO Auto-generated method stub
		if (patientRepository.findByUsername(userDTO.getUsername()) == null) {
			userDTO.setId(0);
			return patientMapper.toDTO(patientRepository.save(patientMapper.toEntityInsert(userDTO)));
		}
		return null;
	}

	public PatientDTO findpatientById(int id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (patient != null) {
			return patientMapper.toDTO(patient);
		}
		return null;
	}

	public List<PatientDTO> viewAll() {
		// TODO Auto-generated method stub
		return patientMapper.toDTOs(patientRepository.findAll());
	}

	public PatientDTO update(PatientDTO patientDTO) {
		// TODO Auto-generated method stub

		Patient patient = patientMapper.toEntityUpdate(patientDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + patientDTO.getId()));
		patient.update(patientDTO);
		patientRepository.save(patient);
		return patientMapper.toDTO(patient);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		List<CaregiverHasPatient> caregiverHasPatients=caregiverHasPatientRepository.findByPatientId(id);
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		caregiverHasPatientRepository.deleteAll(caregiverHasPatients);
		patientRepository.delete(patient);

	}

	public List<PatientDTO> patientWithoutCaregiver(int id) {
		// TODO Auto-generated method stub
		return patientMapper.toDTOs(patientRepository.patientWithoutCaregiver(id));
	}

}
