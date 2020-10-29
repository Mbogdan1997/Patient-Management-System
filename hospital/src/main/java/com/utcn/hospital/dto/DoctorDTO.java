package com.utcn.hospital.dto;

import java.io.Serializable;

import com.utcn.hospital.entity.Doctor;

public class DoctorDTO extends UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3189415229831517839L;

	public DoctorDTO() {
		
	}
	
	public DoctorDTO(Doctor entity) {
		super(entity.getId(),entity.getName(),entity.getBirthDate(),entity.getGender(),
				entity.getAdress(),entity.getUsername(),entity.getPassword(),entity.getRole());
	}

}
