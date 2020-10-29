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

import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.dto.PatientDTO;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.service.MedicationPlanService;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan",produces = {MediaType.APPLICATION_JSON_VALUE})
public class MedicationPlanRestController {
	public MedicationPlanService medicationPlanService;

	@Autowired
	public MedicationPlanRestController(MedicationPlanService medicationPlanService) {
		// TODO Auto-generated constructor stub
		this.medicationPlanService = medicationPlanService;
	}

	@GetMapping()
	public List<MedicationPlanDTO> viewAll() {
		return medicationPlanService.viewAll();

	}

	@GetMapping(value = "/{id}")
	public List<MedicationPlanDTO> findById(@PathVariable("id") Integer id) {
		return medicationPlanService.findForPatient(id);
	}

	@PostMapping()
	public MedicationPlanDTO insertmedicationPlanDTO(@RequestBody MedicationPlanDTO medicationPlanDTO) {
		return medicationPlanService.insert(medicationPlanDTO);
	}

	@PutMapping()
	public MedicationPlanDTO updatemedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO) {
		return medicationPlanService.update(medicationPlanDTO);
	}

	@DeleteMapping()
	public void delete(@RequestBody MedicationPlanDTO medicationPlanDTO) {
		medicationPlanService.delete(medicationPlanDTO);
	}

	@GetMapping("/doctorview")
	public List<MedicationPlanDTO> viewByDoctor(HttpSession session) {

		UserDTO currentUser = (UserDTO) session.getAttribute("user");
		return medicationPlanService.findForDoctor(currentUser.getId());
	}

	@GetMapping("/patientview")
	public List<MedicationPlanDTO> viewByPatient(HttpSession session) {
		UserDTO currentUser = (UserDTO) session.getAttribute("user");
		return medicationPlanService.findForPatient(currentUser.getId());
	}
	
	@GetMapping("day/{id}")
	public List<DateDTO> viewAllDays(@PathVariable int id){
		return medicationPlanService.viewAllDatesForMedicationPlan(id);
	}
	
	

}
