package com.utcn.hospital.dto;

import java.io.Serializable;
import java.util.Date;

import com.utcn.hospital.entity.Patient;
import com.utcn.hospital.enumeration.Gender;
import com.utcn.hospital.enumeration.Role;

public class PatientDTO extends UserDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2191483916188826942L;

	public PatientDTO() {
		
	}

	public PatientDTO(Patient entity) {
		// TODO Auto-generated constructor stub
		
		super(entity.getId(),entity.getName(),entity.getBirthDate(),entity.getGender(),
				entity.getAdress(),entity.getUsername(),entity.getPassword(),entity.getRole());
	}

}
