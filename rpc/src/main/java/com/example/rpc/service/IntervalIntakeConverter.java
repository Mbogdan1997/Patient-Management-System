package com.example.rpc.service;

import java.util.List;

import com.utcn.hospital.dto.IntakeIntervalDTO;

public interface IntervalIntakeConverter {
	
	String[] getColumnName();
	Object[][] getTableData(List<IntakeIntervalDTO> intakeIntervalDTOs);

}
