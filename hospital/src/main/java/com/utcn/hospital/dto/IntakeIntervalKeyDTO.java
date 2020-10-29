package com.utcn.hospital.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.utcn.hospital.entity.IntakeIntervalKey;

public class IntakeIntervalKeyDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787567968604092545L;
	private int drugId;
	private int medicationPlanId;
	
	
	
	public IntakeIntervalKeyDTO() {
		super();
	}



	public IntakeIntervalKeyDTO(IntakeIntervalKey idKey) {
		super();
		this.drugId = idKey.getDrugId();
		this.medicationPlanId = idKey.getMedicationPlanId();
	}



	public int getDrugId() {
		return drugId;
	}



	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}



	public int getMedicationPlanId() {
		return medicationPlanId;
	}



	public void setMedicationPlanId(int medicationPlanId) {
		this.medicationPlanId = medicationPlanId;
	}
	
	
	
	
	


}
