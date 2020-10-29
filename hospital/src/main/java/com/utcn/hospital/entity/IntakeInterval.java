package com.utcn.hospital.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.utcn.hospital.dto.IntakeIntervalDTO;

@Entity
public class IntakeInterval {
	
	@EmbeddedId
	private IntakeIntervalKey id;
	
	@ManyToOne
    @MapsId("drug_id")
    @JoinColumn(name = "drug_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Drug drug;
	
	@ManyToOne
    @MapsId("medication_plan_id")
    @JoinColumn(name = "medication_plan_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MedicationPlan medicationPlan;
	
	
	private int startHour;
	private int finishHour;
	
	
	
	
	
	
	
	
	public IntakeInterval() {
		super();
	}
	public IntakeInterval(IntakeIntervalKey id, Drug drug, MedicationPlan medicationPlan, int startHour,
			int finishHour) {
		super();
		this.id = id;
		this.drug = drug;
		this.medicationPlan = medicationPlan;
		this.startHour = startHour;
		this.finishHour = finishHour;
	}
	public IntakeIntervalKey getId() {
		return id;
	}
	public void setId(IntakeIntervalKey id) {
		this.id = id;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getFinishHour() {
		return finishHour;
	}
	public void setFinishHour(int finishHour) {
		this.finishHour = finishHour;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public MedicationPlan getMedicationPlan() {
		return medicationPlan;
	}
	public void setMedicationPlan(MedicationPlan medicationPlan) {
		this.medicationPlan = medicationPlan;
	}
	
	
	
	public void update(IntakeIntervalDTO intakeIntervalDTO) {
		// TODO Auto-generated method stub
		this.startHour=intakeIntervalDTO.getStartHour();
		this.finishHour=intakeIntervalDTO.getFinishHour();
		
	}
	
	
	
	
	
	

}
