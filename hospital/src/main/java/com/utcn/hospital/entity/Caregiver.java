package com.utcn.hospital.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.sql.Update;

import com.utcn.hospital.dto.CaregiverDTO;

@Entity
public class Caregiver extends User {

	public Caregiver() {

	}

	public Caregiver(CaregiverDTO dto) {
		
		super(dto.getId(),dto.getUsername(),dto.getPassword(),dto.getRole(),
				dto.getName(),dto.getBirthDate(),dto.getGender(),dto.getAdress());
		// TODO Auto-generated constructor stub
	}
	
	public void update(CaregiverDTO dto) {
		this.setUsername(dto.getUsername());
		this.setPassword(dto.getPassword());
		this.setName(dto.getName());
		this.setAdress(dto.getAdress());
		this.setBirthDate(dto.getBirthDate());
		this.setGender(dto.getGender());
	}



}
