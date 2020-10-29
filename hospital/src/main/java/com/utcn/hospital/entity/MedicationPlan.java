package com.utcn.hospital.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.utcn.hospital.dto.MedicationPlanDTO;

@Entity
public class MedicationPlan extends IdEntity {
	
	private Date startDate;
	private Date finishDate;
	
	@ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="doctor_id",referencedColumnName = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Doctor doctor;
	
	
	
	public MedicationPlan() {
		super();
	}
	
	
	
	public MedicationPlan(Date startDate, Date finishDate, Patient patient, Doctor doctor) {
		super();
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.patient = patient;
		this.doctor = doctor;
	}



	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}



	public void update(MedicationPlanDTO medicationPlanDTO) {
		// TODO Auto-generated method stub
		
		this.startDate=medicationPlanDTO.getStartDate();
		this.finishDate=medicationPlanDTO.getFinishDate();
		
	}
	
	
	

}
