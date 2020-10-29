package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.entity.Doctor;
import com.utcn.hospital.entity.MedicationPlan;
import com.utcn.hospital.repository.MedicationPlanRepository;

@Component
public class MedicationPlanMapper implements AbstractMapper<MedicationPlan, MedicationPlanDTO>,ListMapper<MedicationPlan, MedicationPlanDTO> {
	
	private MedicationPlanRepository repository;
	private DoctorMapper doctorMapper;
	private PatientMapper patientMapper;
	
	@Autowired
	public MedicationPlanMapper(MedicationPlanRepository repository, DoctorMapper doctorMapper,PatientMapper patientMapper) {
		// TODO Auto-generated constructor stub
	this.repository=repository;
	this.doctorMapper=doctorMapper;
	this.patientMapper=patientMapper;
	
	}

	@Override
	public Optional<MedicationPlan> toEntityUpdate(MedicationPlanDTO dto) {
		// TODO Auto-generated method stub
		return repository.findById(dto.getId());
	}

	@Override
	public MedicationPlan toEntityInsert(MedicationPlanDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(dto.getDoctorDTO().getId());
		System.out.println(dto.getPatientDTO().getId());

		return new MedicationPlan(dto.getStartDate(),dto.getFinishDate(),
				patientMapper.toEntityUpdate(dto.getPatientDTO()).orElseThrow(() -> new EntityNotFoundException("Cannot find patient with ID: ")),
				doctorMapper.toEntityUpdate(dto.getDoctorDTO()).orElseThrow(() -> new EntityNotFoundException("Cannot find doctor with ID: "))
				);
	}

	@Override
	public MedicationPlanDTO toDTO(MedicationPlan entity) {
		// TODO Auto-generated method stub
		return new MedicationPlanDTO(entity);
	}
	
	@Override
	public List<MedicationPlanDTO> toDTOs(List<MedicationPlan> entities) {
		// TODO Auto-generated method stub
		List<MedicationPlanDTO> medicationPlanDTOs = new ArrayList<MedicationPlanDTO>();
		for (MedicationPlan entity : entities) {
			medicationPlanDTOs.add(this.toDTO(entity));
		}

		return medicationPlanDTOs;
	}

	@Override
	public List<MedicationPlan> toEntities(List<MedicationPlanDTO> dtos) {
		// TODO Auto-generated method stub
		List<MedicationPlan> medicationPlans = new ArrayList<MedicationPlan>();
		for (MedicationPlanDTO dto : dtos) {
			medicationPlans.add(this.toEntityUpdate(dto)
					.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}

		return medicationPlans;
	}

}
