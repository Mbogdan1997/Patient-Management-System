package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.List;

import com.utcn.hospital.entity.Caregiver;
import com.utcn.hospital.entity.Doctor;

public class CaregiverDTO extends UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7735256994562361411L;
	private List<PatientDTO> patientDTOs;
	
	public CaregiverDTO() {
		
	}
	
	public CaregiverDTO(Caregiver entity) {
		super(entity.getId(),entity.getName(),entity.getBirthDate(),entity.getGender(),
				entity.getAdress(),entity.getUsername(),entity.getPassword(),entity.getRole());
	}


	public List<PatientDTO> getPatientDTOs() {
		return patientDTOs;
	}

	public void setPatientDTOs(List<PatientDTO> patientDTOs) {
		this.patientDTOs = patientDTOs;
	}
	
	
	
	

}
