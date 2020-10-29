package com.utcn.hospital.controller.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.IntakeIntervalKeyDTO;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.entity.IntakeIntervalKey;
import com.utcn.hospital.service.IntakeIntervalService;

@RestController
@CrossOrigin
@RequestMapping(value = "/intakeInterval",produces = {MediaType.APPLICATION_JSON_VALUE})
public class IntakeIntervalRestController {

	
	public IntakeIntervalService intakeIntervalService;

	@Autowired
	public IntakeIntervalRestController(IntakeIntervalService intakeIntervalService) {
		// TODO Auto-generated constructor stub
		this.intakeIntervalService = intakeIntervalService;
	}
//
//	@GetMapping()
//	public IntakeIntervalDTO findById(IntakeIntervalKeyDTO id) {
//		return intakeIntervalService.findIntakeIntervalById(id);
//	}

	@PostMapping()
	public IntakeIntervalDTO insertintakeIntervalDTO(@RequestBody IntakeIntervalDTO intakeIntervalDTO) {
		return intakeIntervalService.insert(intakeIntervalDTO);
	}

	@PutMapping()
	public IntakeIntervalDTO updateintakeInterval(@RequestBody IntakeIntervalDTO intakeIntervalDTO) {
		return intakeIntervalService.update(intakeIntervalDTO);
	}

	@DeleteMapping()
	public void delete(@RequestBody IntakeIntervalDTO intakeIntervalDTO) {
		intakeIntervalService.delete(intakeIntervalDTO);
	}

	@CrossOrigin
	@GetMapping("/medicationplan/")
	public List<IntakeIntervalDTO> viewMedicationPlan(MedicationPlanDTO medicationPlanDTO) {
		return intakeIntervalService.viewAllAfterMedicationPlanId(medicationPlanDTO.getId());
	}
	
	@GetMapping("/{id}")
	public List<IntakeIntervalDTO> viewForMedicalPlan(@PathVariable int id){
		System.out.print(id);
	return intakeIntervalService.viewAllAfterMedicationPlanId(id);
	
	}
}
