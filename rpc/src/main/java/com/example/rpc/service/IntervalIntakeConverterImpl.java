package com.example.rpc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.IntakeIntervalDTO;

@Component
public class IntervalIntakeConverterImpl implements IntervalIntakeConverter {

	@Override
	public String[] getColumnName() {
		// TODO Auto-generated method stub
		String[] strings = { "Medication Plan Id", "Drug Id", "Drug Name", "Start Hour", "End Hour" };
		return strings;
	}

	@Override
	public Object[][] getTableData(List<IntakeIntervalDTO> intakeIntervalDTOs) {
		// TODO Auto-generated method stub
		Object[][] dataObjects = new Object[intakeIntervalDTOs.size()][5];

		for (int i = 0; i < intakeIntervalDTOs.size(); i++) {
			IntakeIntervalDTO intakeIntervalDTO = intakeIntervalDTOs.get(i);
			dataObjects[i][0] = intakeIntervalDTO.getMedicationPlanDTO().getId();
			dataObjects[i][1] = intakeIntervalDTO.getDrugDTO().getId();
			dataObjects[i][2] = intakeIntervalDTO.getDrugDTO().getName();
			dataObjects[i][3] = intakeIntervalDTO.getStartHour();
			dataObjects[i][4] = intakeIntervalDTO.getFinishHour();

		}

		return dataObjects;
	}

}
