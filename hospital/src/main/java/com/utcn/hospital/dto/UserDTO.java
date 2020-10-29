package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.Date;

import com.utcn.hospital.entity.User;
import com.utcn.hospital.enumeration.Gender;
import com.utcn.hospital.enumeration.Role;

public class UserDTO extends PersonDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7580141079238461435L;
	private String username;
	private String password;
	private Role role;

	public UserDTO(int id, String name, Date birthDate, Gender gender, String adress, String username, String password,
			Role role) {
		super(id, name, birthDate, gender, adress);
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserDTO() {

	}

	public UserDTO(User entity) {
		super(entity.getId(), entity.getName(), entity.getBirthDate(), entity.getGender(), entity.getAdress());
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.role = entity.getRole();
	}

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

	@Override
	public String toString() {
		return "UserDTO [id=" + this.getId() + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

}
