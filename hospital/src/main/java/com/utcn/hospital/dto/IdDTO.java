package com.utcn.hospital.dto;

import java.io.Serializable;

public class IdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1566235981064337663L;
	private int id;

	public IdDTO(int id) {
		this.id = id;
	}

	public IdDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
