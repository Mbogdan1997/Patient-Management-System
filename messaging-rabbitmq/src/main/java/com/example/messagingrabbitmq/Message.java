package com.example.messagingrabbitmq;

import java.util.Date;

public class Message {
	
	private int patientId;
	private String activityName;
	private Date start;
	private Date end;
	
	
	
	
	
	
	public Message() {
		
	}
	
	
	
	
	public int getPatientId() {
		return patientId;
	}




	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}




	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	

}
