package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.IsTakenDTO;
import com.utcn.hospital.entity.IsTaken;

@Component
public class IsTakenMapper implements ListMapper<IsTaken, IsTakenDTO> {

	@Override
	public List<IsTakenDTO> toDTOs(List<IsTaken> entities) {
		// TODO Auto-generated method stub
		List<IsTakenDTO> isTakenDTOs=new ArrayList<IsTakenDTO>();
		for(IsTaken isTaken:entities) {
			IsTakenDTO isTakenDTO=new IsTakenDTO();
			isTakenDTO.setDrugName(isTaken.getIntakeInterval().getDrug().getName());
			isTakenDTO.setEndHour(isTaken.getIntakeInterval().getFinishHour());
			isTakenDTO.setStartHour(isTaken.getIntakeInterval().getStartHour());
			isTakenDTO.setTaken(isTaken.getTaken());
			isTakenDTOs.add(isTakenDTO);
		}
		return isTakenDTOs;
	}

	@Override
	public List<IsTaken> toEntities(List<IsTakenDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
