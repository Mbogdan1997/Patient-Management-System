package com.utcn.hospital.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.Message;
import com.utcn.hospital.entity.Activity;
import com.utcn.hospital.entity.Patient;
import com.utcn.hospital.repository.ActivityRepository;
import com.utcn.hospital.repository.PatientRepository;

@Component
public class ActivityMapper implements AbstractMapper<Activity,Message>{

	
	private PatientRepository patientRepository;
	
	@Autowired
	public ActivityMapper(PatientRepository patientRepository) {
		// TODO Auto-generated constructor stub
		this.patientRepository=patientRepository;
	}
	
	
	@Override
	public Optional<Activity> toEntityUpdate(Message dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity toEntityInsert(Message dto) {
		// TODO Auto-generated method stub
		Patient patient=patientRepository.findById(dto.getPatientId()).get();
		Activity activity=new Activity(dto.getActivityName(), dto.getStart(), dto.getEnd(), patient);
		
		return activity;
	}

	@Override
	public Message toDTO(Activity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
