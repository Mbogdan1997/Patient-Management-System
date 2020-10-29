package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.Date;

import com.utcn.hospital.enumeration.Gender;
import com.utcn.hospital.enumeration.Role;

public class PersonDTO extends IdDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3282012796481133787L;
	private String name;
	private Date birthDate;
	private Gender gender;
	private String adress;
	
	public PersonDTO() {
		
	}
	
	public PersonDTO(int id, String name, Date birthDate, Gender gender, String adress) {
		super(id);
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.adress = adress;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
