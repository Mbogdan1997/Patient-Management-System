package com.utcn.hospital.entity;

import javax.persistence.Entity;

import com.utcn.hospital.dto.DoctorDTO;

@Entity
public class Doctor extends User {

	public Doctor() {
		
	}
	public Doctor(DoctorDTO dto) {
		// TODO Auto-generated constructor stub
		super(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole(),
				dto.getName(), dto.getBirthDate(), dto.getGender(), dto.getAdress());

	}
	
	public void update(DoctorDTO dto) {
		this.setUsername(dto.getUsername());
		this.setPassword(dto.getPassword());
		this.setName(dto.getName());
		this.setAdress(dto.getAdress());
		this.setBirthDate(dto.getBirthDate());
		//this.setGender(dto.getGender());
	}

}
