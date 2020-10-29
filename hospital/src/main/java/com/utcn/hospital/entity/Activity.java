package com.utcn.hospital.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Activity extends IdEntity{
	
	private String name;
	private Date startDate;
	private Date endDate;
	
	@ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;
	
	private boolean normal;
	
	private String recomandation;
	
	
	
	
	
	public String getRecomandation() {
		return recomandation;
	}

	public void setRecomandation(String recomandation) {
		this.recomandation = recomandation;
	}

	public boolean isNormal() {
		return normal;
	}

	public void setNormal(boolean normal) {
		this.normal = normal;
	}

	public Activity(String name,Date startDate,Date endDate,Patient patient) {
		this.name=name;
		this.startDate=startDate;
		this.endDate=endDate;
		this.patient=patient;
	}
	
	public Activity() {
		
	}

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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public String getNormalDate() {
		final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss";
		final String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

		Date d;
			try {
				d = sdf.parse(startDate.toString());
				sdf.applyPattern(NEW_FORMAT);
				String newDateString = sdf.format(d);
				System.out.println(newDateString);
				return newDateString;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}
	
	
	
	

}
