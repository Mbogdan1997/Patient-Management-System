package com.utcn.hospital.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.IntakeIntervalKeyDTO;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.entity.IntakeInterval;
import com.utcn.hospital.entity.IntakeIntervalKey;
import com.utcn.hospital.entity.IsTaken;
import com.utcn.hospital.entity.MedicationPlan;
import com.utcn.hospital.enumeration.Taken;
import com.utcn.hospital.mapper.IntakeIntervalMapper;
import com.utcn.hospital.repository.IntakeIntervalRepository;
import com.utcn.hospital.repository.IsTakenRepository;

@Service
public class IntakeIntervalService {

	private IntakeIntervalMapper intakeIntervalMapper;
	private IntakeIntervalRepository intakeIntervalRepository;

	@Autowired
	private IsTakenRepository isTakenRepository;

	@Autowired
	public IntakeIntervalService(IntakeIntervalMapper intakeIntervalMapper,
			IntakeIntervalRepository intakeIntervalRepository) {
		super();
		this.intakeIntervalMapper = intakeIntervalMapper;
		this.intakeIntervalRepository = intakeIntervalRepository;
	}

	public IntakeIntervalDTO insert(IntakeIntervalDTO userDTO) {
		// TODO Auto-generated method stub
		IntakeInterval intakeInterval = intakeIntervalRepository.save(intakeIntervalMapper.toEntityInsert(userDTO));

		Instant startInstant = Instant.ofEpochMilli(userDTO.getMedicationPlanDTO().getStartDate().getTime());
		LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
		LocalDate startDate = startLocalDateTime.toLocalDate();

		Instant endInstant = Instant.ofEpochMilli(userDTO.getMedicationPlanDTO().getFinishDate().getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
		LocalDate endDate = localDateTime.toLocalDate();
		System.out.println(startDate);
		System.out.println(endDate);

		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			IsTaken isTaken = new IsTaken();
			isTaken.setDate(Date.from(date.atStartOfDay(defaultZoneId).toInstant()));
			isTaken.setIntakeInterval(intakeInterval);
			isTaken.setTaken(Taken.UNKNOWN);
			isTakenRepository.save(isTaken);
			System.out.println(date);

		}

		return intakeIntervalMapper.toDTO(intakeInterval);

	}

	public List<IntakeIntervalDTO> viewAllAfterMedicationPlanId(int id) {
		// TODO Auto-generated method stub
		return intakeIntervalMapper.toDTOs(intakeIntervalRepository.findByMadicationPlanId(id));
	}

	public void delete(IntakeIntervalDTO intakeIntervalDTO) {
		// TODO Auto-generated method stub

		IntakeInterval intakeInterval = intakeIntervalMapper.toEntityUpdate(intakeIntervalDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: "));
		intakeIntervalRepository.delete(intakeInterval);
	}

	public IntakeIntervalDTO update(IntakeIntervalDTO intakeIntervalDTO) {
		// TODO Auto-generated method stub
		System.out.println(intakeIntervalDTO);
		System.out.println(intakeIntervalDTO.getMedicationPlanDTO().getId());
		System.out.println(intakeIntervalDTO.getDrugDTO().getId());

		IntakeInterval intakeInterval = intakeIntervalMapper.toEntityUpdate(intakeIntervalDTO)
				.orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: "));
		intakeInterval.update(intakeIntervalDTO);
		intakeIntervalRepository.save(intakeInterval);
		return intakeIntervalMapper.toDTO(intakeInterval);
	}

	public List<IntakeIntervalDTO> drugsForUserBetweenDates(Date date, IdDTO user) {
		return intakeIntervalMapper.toDTOs(intakeIntervalRepository.findBeetweenIntervalForUser(date, user.getId()));
	}

}