package com.utcn.hospital.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.dto.IsTakenDTO;
import com.utcn.hospital.service.IsTakenService;

@RestController
@CrossOrigin
@RequestMapping(value = "/isTaken",produces = {MediaType.APPLICATION_JSON_VALUE})
public class IsTakenRestController {
	
	@Autowired
	private IsTakenService isTakenService;
	
	@PostMapping("/{id}")
	public List<IsTakenDTO> viewMedicationPlanForDay(@PathVariable("id") int id,@RequestBody DateDTO dateDTO) {
		System.out.println(dateDTO.getDate());
		return isTakenService.viewMedicationPlanForDay(id, dateDTO.getDate()); 
	}

}
