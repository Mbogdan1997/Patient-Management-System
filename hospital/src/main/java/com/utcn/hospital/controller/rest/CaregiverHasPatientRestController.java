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

import com.utcn.hospital.dto.CaregiverHasPatientDTO;
import com.utcn.hospital.dto.CaregiverHasPatientKeyDTO;
import com.utcn.hospital.service.CaregiverHasPatientService;


@RestController
@CrossOrigin
@RequestMapping(value = "/caregiverHasPatient",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CaregiverHasPatientRestController {
	
	public CaregiverHasPatientService caregiverHasPatientService;
	
	@Autowired
	public CaregiverHasPatientRestController(CaregiverHasPatientService caregiverHasPatientService) {
		// TODO Auto-generated constructor stub
		this.caregiverHasPatientService=caregiverHasPatientService;
	}
	
	@GetMapping()
	public List<CaregiverHasPatientDTO> viewAll(){
		return caregiverHasPatientService.viewAll();
		
	}
	
	@GetMapping("/{id}")
    public List<CaregiverHasPatientDTO> findByCaregiverId(@PathVariable int id){
        return caregiverHasPatientService.findCaregiverHasPatientById(id);
    }
	


    @PostMapping()
    public CaregiverHasPatientDTO insertcaregiverHasPatientDTO(@RequestBody CaregiverHasPatientDTO caregiverHasPatientDTO){
        return caregiverHasPatientService.insert(caregiverHasPatientDTO);
    }

//    @PutMapping()
//    public CaregiverHasPatientDTO updatecaregiverHasPatient(@RequestBody CaregiverHasPatientDTO caregiverHasPatientDTO) {
//        return caregiverHasPatientService.update(caregiverHasPatientDTO);
//    }

    @DeleteMapping()
    public void delete(@RequestBody CaregiverHasPatientDTO caregiverHasPatientDTO){
        caregiverHasPatientService.delete(caregiverHasPatientDTO);
    }
    
    @GetMapping("/{caregiverId}/{patientId}")
    public boolean check(@PathVariable("caregiverId") int caregiverId,@PathVariable("patientId") int patientId) {
    	return caregiverHasPatientService.check(caregiverId, patientId);
    }
    
    



}