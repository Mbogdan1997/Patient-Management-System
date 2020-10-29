package com.utcn.hospital.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.DrugDTO;
import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.entity.Drug;
import com.utcn.hospital.entity.IntakeInterval;
import com.utcn.hospital.mapper.DrugMapper;
import com.utcn.hospital.repository.DrugRepository;
import com.utcn.hospital.repository.IntakeIntervalRepository;


@Service
public class DrugService {
	
	private DrugMapper drugMapper;
	private DrugRepository drugRepository;
	private IntakeIntervalRepository intakeIntervalRepository;

	@Autowired
	public DrugService(DrugMapper drugMapper, DrugRepository drugRepository,IntakeIntervalRepository intakeIntervalRepository) {
		super();
		this.drugMapper = drugMapper;
		this.drugRepository = drugRepository;
		this.intakeIntervalRepository=intakeIntervalRepository;
	}

	public DrugDTO findDrugById(int id) {
		Drug drug = drugRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (drug != null) {
			return drugMapper.toDTO(drug);
		}
		return null;
	}

	public DrugDTO insert(DrugDTO userDTO) {
		// TODO Auto-generated method stub
		if (drugRepository.findByName(userDTO.getName()) == null) {
			return drugMapper.toDTO(drugRepository.save(drugMapper.toEntityInsert(userDTO)));
		}
		return null;
	}

	public DrugDTO finddrugById(int id) {
		Drug drug = drugRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (drug != null) {
			return drugMapper.toDTO(drug);
		}
		return null;
	}

	public List<DrugDTO> viewAll() {
		// TODO Auto-generated method stub
		return drugMapper.toDTOs(drugRepository.findAll());
	}

	public DrugDTO update(DrugDTO drugDTO) {
		// TODO Auto-generated method stub

		Drug drug = drugMapper.toEntityUpdate(drugDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + drugDTO.getId()));
		drug.update(drugDTO);
		drugRepository.save(drug);
		return drugMapper.toDTO(drug);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		List<IntakeInterval> intakeIntervals=intakeIntervalRepository.findByDrugId(id);
		Drug drug = drugRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		intakeIntervalRepository.deleteAll(intakeIntervals);
		drugRepository.delete(drug);
	}
	
	public List<DrugDTO> findDrugInMedicationPlan(int id){
		return drugMapper.toDTOs(drugRepository.findDrugsInMedicalPlan(id));
	}
	
	public List<DrugDTO> findDrugNotInMedicalPlan(int id){
		return drugMapper.toDTOs(drugRepository.findDrugsNotInMedicalPlan(id));
	}

}
