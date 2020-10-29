package com.utcn.hospital.dto;

import java.util.Date;

public class ActivityDTO extends IdDTO{
	
	private String name;
	private String recomandation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isNormal() {
		return normal;
	}
	public void setNormal(boolean normal) {
		this.normal = normal;
	}
	private Date startDate;
	private Date endDate;
	private boolean normal;
	public String getRecomandation() {
		return recomandation;
	}
	public void setRecomandation(String recomandation) {
		this.recomandation = recomandation;
	}
	
	

}
