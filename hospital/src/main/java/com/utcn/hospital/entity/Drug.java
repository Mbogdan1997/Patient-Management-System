package com.utcn.hospital.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.utcn.hospital.dto.DrugDTO;

@Entity
public class Drug extends IdEntity{
	
	private String name;
	private String sideEffects;
	private String dossage;
	
	
	

	public Drug() {
		super();
	}

	public Drug(DrugDTO dto) {
		// TODO Auto-generated constructor stub
		
		super(dto.getId());
		this.name=dto.getName();
		this.dossage=dto.getDossage();
		this.sideEffects=dto.getSideEffects();
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

	public void update(DrugDTO drugDTO) {
		// TODO Auto-generated method stub
		
		this.dossage=drugDTO.getDossage();
		this.name=drugDTO.getName();
		this.sideEffects=drugDTO.getSideEffects();
		
	}


	
	
	
	
	

}
