package com.utcn.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utcn.hospital.dto.DoctorDTO;
import com.utcn.hospital.dto.UserDTO;

import com.utcn.hospital.entity.Doctor;
import com.utcn.hospital.mapper.DoctorMapper;
import com.utcn.hospital.repository.CaregiverRepository;
import com.utcn.hospital.repository.DoctorRepository;
import com.utcn.hospital.repository.PatientRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class DoctorService {

	private DoctorMapper doctorMapper;
	private DoctorRepository doctorRepository;
	private CaregiverRepository caregiverRepository;
	private PatientRepository patientRepository;

	@Autowired
	public DoctorService(DoctorMapper doctorMapper, DoctorRepository doctorRepository) {
		super();
		this.doctorMapper = doctorMapper;
		this.doctorRepository = doctorRepository;
	}

	public DoctorDTO findDoctorById(int id) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (doctor != null) {
			return doctorMapper.toDTO(doctor);
		}
		return null;
	}

	public DoctorDTO insert(DoctorDTO userDTO) {
		// TODO Auto-generated method stub
		if (doctorRepository.findByUsername(userDTO.getUsername()) == null) {
			return doctorMapper.toDTO(doctorRepository.save(doctorMapper.toEntityInsert(userDTO)));
		}
		return null;
	}

	public DoctorDTO finddoctorById(int id) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (doctor != null) {
			return doctorMapper.toDTO(doctor);
		}
		return null;
	}

	public List<DoctorDTO> viewAll() {
		// TODO Auto-generated method stub
		return doctorMapper.toDTOs(doctorRepository.findAll());
	}

	public DoctorDTO update(DoctorDTO doctorDTO) {
		// TODO Auto-generated method stub

		Doctor doctor = doctorMapper.toEntityUpdate(doctorDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + doctorDTO.getId()));
		doctor.update(doctorDTO);
		doctorRepository.save(doctor);
		return doctorMapper.toDTO(doctor);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		doctorRepository.delete(doctor);

	}

}
