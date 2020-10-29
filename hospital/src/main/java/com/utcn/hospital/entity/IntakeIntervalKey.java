package com.utcn.hospital.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.IntakeIntervalKeyDTO;


@Embeddable
public class IntakeIntervalKey implements Serializable{
	
	@Column(name="drug_id")
	private int drugId;
	
	@Column(name="medication_plan_id")
	private int medicationPlanId;
	
	

	public IntakeIntervalKey() {
		super();
	}

	public IntakeIntervalKey(int drugId,int mpId) {
		super();
		this.drugId = drugId;
		this.medicationPlanId = mpId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drugId;
		result = prime * result + medicationPlanId;
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
		IntakeIntervalKey other = (IntakeIntervalKey) obj;
		if (drugId != other.drugId)
			return false;
		if (medicationPlanId != other.medicationPlanId)
			return false;
		return true;
	}
	
	
	
	

}
