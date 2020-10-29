package com.utcn.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.enumeration.Gender;
import com.utcn.hospital.enumeration.Role;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {
	
	
	
	
	
	public User() {
		super();
	}
	
	
	public User(int id,String username, String password, Role role,String name, Date birthDate, Gender gender, String adress) {
		super(id,name,birthDate,gender,adress);
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public User(UserDTO dto) {
		super(dto.getId(),dto.getName(),dto.getBirthDate(),dto.getGender(),dto.getAdress());
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.role = dto.getRole();
	}
	private String username;
	private String password;
	private Role role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
