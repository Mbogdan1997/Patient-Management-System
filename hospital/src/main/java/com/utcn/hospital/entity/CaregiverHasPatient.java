package com.utcn.hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.utcn.hospital.dto.CaregiverHasPatientKeyDTO;


@Entity
public class CaregiverHasPatient {
	
	@EmbeddedId
	private CaregiverHasPatientKey id;
	
	@ManyToOne
    @MapsId("caregiver_id")
    @JoinColumn(name = "caregiver_id",referencedColumnName = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Caregiver caregiver;
	
	@ManyToOne
    @MapsId("patient_id")
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;
	
	
	

	public CaregiverHasPatient() {
		super();
	}

	public CaregiverHasPatient(CaregiverHasPatientKeyDTO id, Caregiver caregiver, Patient patient) {
		super();
		this.id = new CaregiverHasPatientKey(id);
		this.caregiver = caregiver;
		this.patient = patient;
	}

	public CaregiverHasPatientKey getId() {
		return id;
	}

	public void setId(CaregiverHasPatientKey id) {
		this.id = id;
	}

	public Caregiver getCaregiver() {
		return caregiver;
	}

	public void setCaregiver(Caregiver caregiver) {
		this.caregiver = caregiver;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	

	

}
