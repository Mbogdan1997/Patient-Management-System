package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.DoctorDTO;
import com.utcn.hospital.entity.Doctor;
import com.utcn.hospital.repository.DoctorRepository;

@Component
public class DoctorMapper implements AbstractMapper<Doctor, DoctorDTO>,ListMapper<Doctor, DoctorDTO> {
	
	private DoctorRepository doctorRepository;
	public DoctorMapper(DoctorRepository doctorRepository) {
		// TODO Auto-generated constructor stub
		this.doctorRepository=doctorRepository;
	}

	@Override
	public Optional<Doctor> toEntityUpdate(DoctorDTO dto) {
		// TODO Auto-generated method stub
		return doctorRepository.findById(dto.getId());
	}

	@Override
	public Doctor toEntityInsert(DoctorDTO dto) {
		// TODO Auto-generated method stub
		return new Doctor(dto);
	}

	@Override
	public DoctorDTO toDTO(Doctor entity) {
		// TODO Auto-generated method stub
		return new DoctorDTO(entity);
	}
	
	@Override
	public List<DoctorDTO> toDTOs(List<Doctor> entities) {
		// TODO Auto-generated method stub
		List<DoctorDTO> doctorDTOs=new ArrayList<DoctorDTO>();
		for(Doctor entity:entities) {
			doctorDTOs.add(this.toDTO(entity));
		}
		
		return doctorDTOs;
	}

	@Override
	public List<Doctor> toEntities(List<DoctorDTO> dtos) {
		// TODO Auto-generated method stub
		List<Doctor> doctors=new ArrayList<Doctor>();
		for(DoctorDTO dto:dtos) {
			doctors.add(this.toEntityUpdate(dto).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + dto.getId())));
		}
		
		return doctors;
	}

}
