package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.entity.IntakeInterval;
import com.utcn.hospital.entity.IntakeIntervalKey;
import com.utcn.hospital.repository.IntakeIntervalRepository;

@Component
public class IntakeIntervalMapper
		implements AbstractMapper<IntakeInterval, IntakeIntervalDTO>, ListMapper<IntakeInterval, IntakeIntervalDTO> {

	private DrugMapper drugMapper;
	private MedicationPlanMapper medicationPlanMapper;
	private IntakeIntervalRepository repository;

	@Autowired
	public IntakeIntervalMapper(com.utcn.hospital.mapper.DrugMapper drugMapper,
			MedicationPlanMapper medicationPlanMapper, IntakeIntervalRepository repository) {
		super();
		this.drugMapper = drugMapper;
		this.medicationPlanMapper = medicationPlanMapper;
		this.repository = repository;
	}

	@Override
	public Optional<IntakeInterval> toEntityUpdate(IntakeIntervalDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(dto.getDrugDTO().getId());
		System.out.println(dto.getMedicationPlanDTO().getId());
		return repository.findById(new IntakeIntervalKey(dto.getDrugDTO().getId(),dto.getMedicationPlanDTO().getId()));
	}

	@Override
	public IntakeInterval toEntityInsert(IntakeIntervalDTO dto) {
		// TODO Auto-generated method stub
		System.out.println(dto.getDrugDTO().getId());
		System.out.println(dto.getMedicationPlanDTO().getId());
		return new IntakeInterval(new IntakeIntervalKey(dto.getDrugDTO().getId(),dto.getMedicationPlanDTO().getId()),
				drugMapper.toEntityUpdate(dto.getDrugDTO()).get(),
				medicationPlanMapper.toEntityUpdate(dto.getMedicationPlanDTO()).get(), dto.getStartHour(),
				dto.getFinishHour());
	}

	@Override
	public IntakeIntervalDTO toDTO(IntakeInterval entity) {
		// TODO Auto-generated method stub
		return new IntakeIntervalDTO(entity);
	}

	@Override
	public List<IntakeIntervalDTO> toDTOs(List<IntakeInterval> entities) {
		// TODO Auto-generated method stub
		List<IntakeIntervalDTO> intakeIntervalDTOs = new ArrayList<IntakeIntervalDTO>();
		System.out.print("aici");
		for (IntakeInterval entity : entities) {
			System.out.print(entity.getId());
			intakeIntervalDTOs.add(this.toDTO(entity));
		}

		return intakeIntervalDTOs;
	}

	@Override
	public List<IntakeInterval> toEntities(List<IntakeIntervalDTO> dtos) {
		// TODO Auto-generated method stub
		List<IntakeInterval> intakeIntervals = new ArrayList<IntakeInterval>();
		for (IntakeIntervalDTO dto : dtos) {
			intakeIntervals.add(this.toEntityUpdate(dto)
					.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " )));
		}

		return intakeIntervals;
	}

}
