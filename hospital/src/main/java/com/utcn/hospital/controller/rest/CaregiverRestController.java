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

import com.utcn.hospital.dto.CaregiverDTO;
import com.utcn.hospital.service.CaregiverService;


@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CaregiverRestController {
	
	public CaregiverService caregiverService;
	
	@Autowired
	public CaregiverRestController(CaregiverService caregiverService) {
		// TODO Auto-generated constructor stub
		this.caregiverService=caregiverService;
	}
	
	@GetMapping()
	public List<CaregiverDTO> viewAll(){
		return caregiverService.viewAll();
		
	}
	
	@GetMapping(value = "/{id}")
    public CaregiverDTO findById(@PathVariable("id") Integer id){
        return caregiverService.findCaregiverById(id);
    }
	


    @PostMapping()
    public CaregiverDTO insertcaregiverDTO(@RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.insert(caregiverDTO);
    }

    @PutMapping()
    public CaregiverDTO updatecaregiver(@RequestBody CaregiverDTO caregiverDTO) {
        return caregiverService.update(caregiverDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        caregiverService.delete(id);
    }



}
