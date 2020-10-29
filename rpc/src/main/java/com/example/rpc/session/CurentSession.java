package com.example.rpc.session;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.UserDTO;

@Component
public class CurentSession {

	private static UserDTO curentUser;
	private static int curentHour=new Date().getHours();
	private static List<IntakeIntervalDTO> intakeIntervalDTOs;

	public static void setCurentUser(UserDTO curentUser) {
		CurentSession.curentUser = curentUser;
	}

	public static UserDTO getCurentUser() {
		return curentUser;
	}

	public static int getCurentHour() {
		return curentHour;
	}

	public static void setCurentHour(int curentHour) {
		CurentSession.curentHour = curentHour;
	}

	public static List<IntakeIntervalDTO> getIntakeIntervalDTOs() {
		return intakeIntervalDTOs;
	}

	public static void setIntakeIntervalDTOs(List<IntakeIntervalDTO> intakeIntervalDTOs) {
		CurentSession.intakeIntervalDTOs = intakeIntervalDTOs;
	}
	
	
	
	

}
