package com.utcn.hospital.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.utcn.hospital.entity.Caregiver;
import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.entity.CaregiverHasPatientKey;
import com.utcn.hospital.entity.Patient;

public class CaregiverHasPatientDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 85977079780950017L;
	private CaregiverHasPatientKeyDTO id;
	private CaregiverDTO caregiverDTO;
	private PatientDTO patientDTO;
	
	
	
	public CaregiverHasPatientDTO() {
		super();
	}

	public CaregiverHasPatientDTO(CaregiverHasPatient entity) {
		id=new CaregiverHasPatientKeyDTO(entity.getId());
		caregiverDTO=new CaregiverDTO(entity.getCaregiver());
		patientDTO=new PatientDTO(entity.getPatient());
		
	}

	public CaregiverHasPatientKeyDTO getId() {
		return id;
	}

	public void setId(CaregiverHasPatientKeyDTO id) {
		this.id = id;
	}

	public CaregiverDTO getCaregiverDTO() {
		return caregiverDTO;
	}

	public void setCaregiverDTO(CaregiverDTO caregiverDTO) {
		this.caregiverDTO = caregiverDTO;
	}

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}
	
	

}
