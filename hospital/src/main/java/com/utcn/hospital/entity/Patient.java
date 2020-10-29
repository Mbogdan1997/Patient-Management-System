package com.utcn.hospital.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.utcn.hospital.dto.PatientDTO;

@Entity
public class Patient extends User {
	
	public Patient() {
		
	}
	
	public Patient(PatientDTO patientDTO) {
		
		super(patientDTO.getId(),patientDTO.getUsername(),patientDTO.getPassword(),patientDTO.getRole(),
				patientDTO.getName(),patientDTO.getBirthDate(),patientDTO.getGender(),patientDTO.getAdress());
		
	}
	
	public void update(PatientDTO dto) {
		this.setUsername(dto.getUsername());
		this.setPassword(dto.getPassword());
		this.setName(dto.getName());
		this.setAdress(dto.getAdress());
		this.setBirthDate(dto.getBirthDate());
		this.setGender(dto.getGender());
	}
	
//	@OneToMany(mappedBy = "doctor")
//	private Set<Doctor> doctors;
//	
//	@ManyToMany
//	private Set<Caregiver> caregivers;

}
