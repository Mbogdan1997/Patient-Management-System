package com.utcn.hospital.dto;

import java.io.Serializable;

import com.utcn.hospital.entity.CaregiverHasPatientKey;

public class CaregiverHasPatientKeyDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4808646189296840403L;
	private int caregiverId;
	private int patientId;
	
	public CaregiverHasPatientKeyDTO() {
		super();
	}

	public CaregiverHasPatientKeyDTO(CaregiverHasPatientKey id) {
		super();
		this.caregiverId = id.getCaregiverId();
		this.patientId = id.getPatientId();
	}

	public CaregiverHasPatientKeyDTO(int caregiverId, int patientId) {
		this.caregiverId=caregiverId;
		this.patientId=patientId;
		// TODO Auto-generated constructor stub
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

}
