package com.utcn.hospital.dto;

import com.utcn.hospital.enumeration.Taken;

public class IsTakenDTO {
	
	private String drugName;
	private int startHour;
	private int endHour;
	private Taken taken;
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	public Taken getTaken() {
		return taken;
	}
	public void setTaken(Taken taken) {
		this.taken = taken;
	}
	
	

}
