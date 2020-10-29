package com.utcn.hospital.dto;

import java.io.Serializable;

public class RMINotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5979787718338861153L;
	private String patientName;
	private String drugName;
	private int startHour;
	private int finishHour;
	private boolean takeIt;

	public boolean isTakeIt() {
		return takeIt;
	}

	public void setTakeIt(boolean takeIt) {
		this.takeIt = takeIt;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

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

	public int getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(int finishHour) {
		this.finishHour = finishHour;
	}

}
