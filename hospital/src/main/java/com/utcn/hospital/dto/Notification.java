package com.utcn.hospital.dto;

import java.io.Serializable;

public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174285950624820404L;
	private String patientName;
	private String period;
	private String activityName;
	private int patientId;

	public Notification() {

	}

	public Notification(String patientName, String period, String activityName, int patientId) {
		super();
		this.patientName = patientName;
		this.period = period;
		this.activityName = activityName;
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

}
