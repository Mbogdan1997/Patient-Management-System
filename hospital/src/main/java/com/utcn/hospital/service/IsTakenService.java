package com.utcn.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.IsTakenDTO;
import com.utcn.hospital.mapper.IsTakenMapper;
import com.utcn.hospital.repository.IsTakenRepository;

@Service
public class IsTakenService {
	
	@Autowired
	private IsTakenRepository isTakenRepository;
	@Autowired
	private IsTakenMapper isTakenMapper;
	
	
	public List<IsTakenDTO> viewMedicationPlanForDay(int id, String date){
		String today[]=date.split("-");
		return isTakenMapper.toDTOs(isTakenRepository.viewMedicationPlanStatus(Integer.parseInt(today[2]),
				Integer.parseInt(today[1]), Integer.parseInt(today[0]), id));
	}

}
