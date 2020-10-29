package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.utcn.hospital.dto.DrugDTO;
import com.utcn.hospital.entity.Drug;
import com.utcn.hospital.repository.DrugRepository;

@Component
public class DrugMapper implements AbstractMapper<Drug, DrugDTO>, ListMapper<Drug, DrugDTO> {

	private DrugRepository drugRepository;

	@Autowired
	public DrugMapper(DrugRepository drugRepository) {
		// TODO Auto-generated constructor stub
		this.drugRepository = drugRepository;
	}

	@Override
	public Optional<Drug> toEntityUpdate(DrugDTO dto) {
		// TODO Auto-generated method stub
		return drugRepository.findById(dto.getId());
	}

	@Override
	public Drug toEntityInsert(DrugDTO dto) {
		// TODO Auto-generated method stub
		return new Drug(dto);
	}

	@Override
	public DrugDTO toDTO(Drug entity) {
		// TODO Auto-generated method stub
		return new DrugDTO(entity);
	}

	@Override
	public List<DrugDTO> toDTOs(List<Drug> entities) {
		// TODO Auto-generated method stub
		List<DrugDTO> drugDTOs = new ArrayList<DrugDTO>();
		for (Drug entity : entities) {
			System.out.print(entity.getId());
			drugDTOs.add(this.toDTO(entity));
		}

		return drugDTOs;
	}

	@Override
	public List<Drug> toEntities(List<DrugDTO> dtos) {
		// TODO Auto-generated method stub
		List<Drug> drugs = new ArrayList<Drug>();
		for (DrugDTO dto : dtos) {
			drugs.add(this.toEntityUpdate(dto)
					.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}

		return drugs;
	}

}
