package com.utcn.hospital.dto;

public class ChartItemDTO {
	
	private String activityName;
	private int periodInMinutes;
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public int getPeriodInMinutes() {
		return periodInMinutes;
	}
	public void setPeriodInMinutes(int periodInMinutes) {
		this.periodInMinutes = periodInMinutes;
	}
	

}
