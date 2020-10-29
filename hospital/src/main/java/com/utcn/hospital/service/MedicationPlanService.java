package com.utcn.hospital.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.dto.PatientDTO;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.entity.Doctor;
import com.utcn.hospital.entity.IsTaken;
import com.utcn.hospital.entity.MedicationPlan;
import com.utcn.hospital.entity.Patient;
import com.utcn.hospital.enumeration.Taken;
import com.utcn.hospital.mapper.MedicationPlanMapper;
import com.utcn.hospital.repository.DoctorRepository;
import com.utcn.hospital.repository.MedicationPlanRepository;
import com.utcn.hospital.repository.PatientRepository;

@Service
public class MedicationPlanService {
			
	private MedicationPlanMapper medicationPlanMapper;
	private MedicationPlanRepository medicationPlanRepository;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;

	@Autowired
	public MedicationPlanService(PatientRepository patientRepository,DoctorRepository doctorRepository,MedicationPlanMapper medicationPlanMapper, MedicationPlanRepository medicationPlanRepository) {
		super();
		this.doctorRepository=doctorRepository;
		this.patientRepository=patientRepository;
		this.medicationPlanMapper = medicationPlanMapper;
		this.medicationPlanRepository = medicationPlanRepository;
	}

	public MedicationPlanDTO findMedicationPlanById(int id) {
		MedicationPlan medicationPlan = medicationPlanRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (medicationPlan != null) {
			return medicationPlanMapper.toDTO(medicationPlan);
		}
		return null;
	}

	public MedicationPlanDTO insert(MedicationPlanDTO medicationPlanDTO) {
		// TODO Auto-generated method stub
		System.out.println(medicationPlanDTO.getPatientDTO().getId());
			return medicationPlanMapper.toDTO(medicationPlanRepository.save(medicationPlanMapper.toEntityInsert(medicationPlanDTO)));
	
	}

	public MedicationPlanDTO findmedicationPlanById(int id) {
		MedicationPlan medicationPlan = medicationPlanRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + id));
		if (medicationPlan != null) {
			return medicationPlanMapper.toDTO(medicationPlan);
		}
		return null;
	}

	public List<MedicationPlanDTO> viewAll() {
		// TODO Auto-generated method stub
		return medicationPlanMapper.toDTOs(medicationPlanRepository.findAll());
	}

	public MedicationPlanDTO update(MedicationPlanDTO medicationPlanDTO) {
		// TODO Auto-generated method stub

		MedicationPlan medicationPlan = medicationPlanMapper.toEntityUpdate(medicationPlanDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + medicationPlanDTO.getId()));
		medicationPlan.update(medicationPlanDTO);
		medicationPlanRepository.save(medicationPlan);
		return medicationPlanMapper.toDTO(medicationPlan);
	}

	public void delete(MedicationPlanDTO medicationPlanDTO) {
		// TODO Auto-generated method stub

		MedicationPlan medicationPlan = medicationPlanMapper.toEntityUpdate(medicationPlanDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + medicationPlanDTO.getId()));
		medicationPlanRepository.delete(medicationPlan);

	}
	
	public List<MedicationPlanDTO> findForDoctor(int doctorId) {
		return medicationPlanMapper.toDTOs(medicationPlanRepository.findByDoctorId(doctorId));
	}
	
	public List<MedicationPlanDTO> findForPatient(int patientId) {
		return medicationPlanMapper.toDTOs(medicationPlanRepository.findByPatientId(patientId));
	}

	public List<MedicationPlanDTO> findmedicationPlanForUser(Integer id) {
		// TODO Auto-generated method stub
		return medicationPlanMapper.toDTOs(medicationPlanRepository.findByPatientId(id));
	}
	
	public List<DateDTO> viewAllDatesForMedicationPlan(int patientId){
		
//		MedicationPlan medicationPlan=medicationPlanRepository.findById(medicationPlanId).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + medicationPlanId));
//		Instant startInstant = Instant.ofEpochMilli(medicationPlan.getStartDate().getTime());
//		LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
//		LocalDate startDate = startLocalDateTime.toLocalDate();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//
//		Instant endInstant = Instant.ofEpochMilli(medicationPlan.getFinishDate().getTime());
//		LocalDateTime localDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
//		LocalDate endDate = localDateTime.toLocalDate();
//		System.out.println(startDate);
//		System.out.println(endDate);
//		List<DateDTO> dateDTOs=new ArrayList<DateDTO>();
//
//		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
//			ZoneId defaultZoneId = ZoneId.systemDefault();
//			Date date2=Date.from(date.atStartOfDay(defaultZoneId).toInstant());
//			System.out.println(date2);
//			DateDTO dateDTO=new DateDTO();
//			dateDTO.setDate(dateFormat.format(date2).toString());
//			dateDTOs.add(dateDTO);
//
//		}
//		return dateDTOs;
		
		Date minDate=medicationPlanRepository.findMinDate(patientId);
		Instant startInstant = Instant.ofEpochMilli(minDate.getTime());
		LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
		LocalDate startDate = startLocalDateTime.toLocalDate();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date maxDate=medicationPlanRepository.findMaxDate(patientId);
		Instant endInstant = Instant.ofEpochMilli(maxDate.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
		LocalDate endDate = localDateTime.toLocalDate();
		System.out.println(startDate);
		System.out.println(endDate);
		List<DateDTO> dateDTOs=new ArrayList<DateDTO>();

		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date date2=Date.from(date.atStartOfDay(defaultZoneId).toInstant());
			System.out.println(date2);
			DateDTO dateDTO=new DateDTO();
			dateDTO.setDate(dateFormat.format(date2).toString());
			dateDTOs.add(dateDTO);

		}
		return dateDTOs;
		
	}

}
