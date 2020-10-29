package com.utcn.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.utcn.hospital.enumeration.Taken;

@Entity
public class IsTaken extends IdEntity{
	
	private Taken taken;
	private Date date;
	@ManyToOne
	@JoinColumns(value = {
	          @JoinColumn(name="drug_id",referencedColumnName = "drug_id"),
	          @JoinColumn(name = "medication_plan_id", referencedColumnName = "medication_plan_id") })
	private IntakeInterval intakeInterval;
	
	public Taken getTaken() {
		return taken;
	}
	public void setTaken(Taken taken) {
		this.taken = taken;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public IntakeInterval getIntakeInterval() {
		return intakeInterval;
	}
	public void setIntakeInterval(IntakeInterval intakeInterval) {
		this.intakeInterval = intakeInterval;
	}
	
	
	
	

}
