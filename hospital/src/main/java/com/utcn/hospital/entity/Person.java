package com.utcn.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.enumeration.Gender;
import com.utcn.hospital.enumeration.Role;

@MappedSuperclass
public class Person extends IdEntity{
	
	private String name;
	private Date birthDate;
	private Gender gender;
	private String adress;
	
	
	public Person() {
		
	}
	public Person(int id, String name, Date birthDate, Gender gender, String adress) {
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
