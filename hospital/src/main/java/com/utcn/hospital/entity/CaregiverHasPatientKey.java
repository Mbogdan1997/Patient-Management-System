package com.utcn.hospital.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.utcn.hospital.dto.CaregiverHasPatientKeyDTO;
import com.utcn.hospital.dto.IdDTO;

@Embeddable
public class CaregiverHasPatientKey implements Serializable {
	
	@Column(name="caregiver_id")
	private int caregiverId;
	
	@Column(name="patient_id")
	private int patientId;

	public CaregiverHasPatientKey() {
		super();
	}
	
	public CaregiverHasPatientKey(int caregiverId,int patientId) {
		this.caregiverId=caregiverId;
		this.patientId=patientId;
	}

	public CaregiverHasPatientKey(CaregiverHasPatientKeyDTO iDto) {
		super();
		this.caregiverId = iDto.getCaregiverId();
		this.patientId = iDto.getPatientId();
	}

	public int getCaregiverId() {
		return caregiverId;
	}

	public void setCaregiverId(int caregiverId) {
		this.caregiverId = caregiverId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caregiverId;
		result = prime * result + patientId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaregiverHasPatientKey other = (CaregiverHasPatientKey) obj;
		if (caregiverId != other.caregiverId)
			return false;
		if (patientId != other.patientId)
			return false;
		return true;
	}
	
	
	

}
