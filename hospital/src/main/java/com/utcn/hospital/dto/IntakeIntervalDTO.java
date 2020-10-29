package com.utcn.hospital.dto;

import java.io.Serializable;

import com.utcn.hospital.entity.IntakeInterval;

public class IntakeIntervalDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2031343540370454938L;
	private IntakeIntervalKeyDTO iDto;
	private DrugDTO drugDTO;
	private MedicationPlanDTO medicationPlanDTO;
	private int startHour;
	private int finishHour;
	
	
	
	public IntakeIntervalDTO() {
		super();
	}


	public IntakeIntervalDTO(IntakeInterval entity) {
		this.iDto=new IntakeIntervalKeyDTO(entity.getId());
		this.drugDTO=new DrugDTO(entity.getDrug());
		this.medicationPlanDTO=new MedicationPlanDTO(entity.getMedicationPlan());
		this.startHour=entity.getStartHour();
		this.finishHour=entity.getFinishHour();
	}
	
	
	public DrugDTO getDrugDTO() {
		return drugDTO;
	}
	public void setDrugDTO(DrugDTO drugDTO) {
		this.drugDTO = drugDTO;
	}
	public MedicationPlanDTO getMedicationPlanDTO() {
		return medicationPlanDTO;
	}
	public void setMedicationPlanDTO(MedicationPlanDTO medicationPlanDTO) {
		this.medicationPlanDTO = medicationPlanDTO;
	}


	public IntakeIntervalKeyDTO getiDto() {
		return iDto;
	}


	public void setiDto(IntakeIntervalKeyDTO iDto) {
		this.iDto = iDto;
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
	
	
	
	

}
