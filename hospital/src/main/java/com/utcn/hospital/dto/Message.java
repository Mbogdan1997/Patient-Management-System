package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2469352308691931894L;
	private int patientId;
	private String activityName;
	private Date start;
	private Date end;
	
	public Message(Date start,Date end, String activityName) {
		this.activityName=activityName;
		this.start=start;
		this.end=end;
	}
	
	
	
	
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




	@Override
	public String toString() {
		return "Message [patientId="+patientId+", activityName=" + activityName + ", start=" + start + ", end=" + end + "]";
	}
	
	
	

}