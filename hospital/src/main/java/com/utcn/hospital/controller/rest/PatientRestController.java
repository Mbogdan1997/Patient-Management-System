package com.utcn.hospital.controller.rest;

import java.util.List;

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

import com.utcn.hospital.dto.PatientDTO;
import com.utcn.hospital.service.PatientService;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient",produces = {MediaType.APPLICATION_JSON_VALUE})
public class PatientRestController {
	
	public PatientService patientService;
	
	@Autowired
	public PatientRestController(PatientService patientService) {
		// TODO Auto-generated constructor stub
		this.patientService=patientService;
	}
	
	@GetMapping()
	public List<PatientDTO> viewAll(){
		System.out.print("Aici");
		return patientService.viewAll();
		
	}
	
	@GetMapping(value = "/{id}")
    public PatientDTO findById(@PathVariable("id") Integer id){
        return patientService.findpatientById(id);
    }
	


    @PostMapping()
    public PatientDTO insertpatientDTO(@RequestBody PatientDTO patientDTO){
        return patientService.insert(patientDTO);
    }

    @PutMapping()
    public PatientDTO updatepatient(@RequestBody PatientDTO patientDTO) {
        return patientService.update(patientDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id){
        patientService.delete(id);
    }
    
    @GetMapping("/caregiver/not/{id}")
    public List<PatientDTO> patientWithoutCaregiver(@PathVariable("id") int id){
    	return patientService.patientWithoutCaregiver(id);
    }
    
}