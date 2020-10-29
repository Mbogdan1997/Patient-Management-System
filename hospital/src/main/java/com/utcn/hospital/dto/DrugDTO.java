package com.utcn.hospital.dto;

import java.io.Serializable;

import com.utcn.hospital.entity.Drug;

public class DrugDTO extends IdDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6616086114958738651L;
	/**
	 * 
	 */
	private String name;
	private String sideEffects;
	private String dossage;
	
	
	

	public DrugDTO() {
		super();
	}

	public DrugDTO(Drug entity) {
		
		super(entity.getId());
		this.name=entity.getName();
		this.dossage=entity.getDossage();
		this.sideEffects=entity.getSideEffects();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSideEffects() {
		return sideEffects;
	}

	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}

	public String getDossage() {
		return dossage;
	}

	public void setDossage(String dossage) {
		this.dossage = dossage;
	}


}
