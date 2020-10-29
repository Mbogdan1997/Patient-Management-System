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

import com.utcn.hospital.dto.DoctorDTO;
import com.utcn.hospital.service.DoctorService;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor",produces = {MediaType.APPLICATION_JSON_VALUE})
public class DoctorRestController {
	
	public DoctorService doctorService;
	
	@Autowired
	public DoctorRestController(DoctorService doctorService) {
		// TODO Auto-generated constructor stub
		this.doctorService=doctorService;
	}
	
	@GetMapping()
	public List<DoctorDTO> viewAll(){
		return doctorService.viewAll();
		
	}
	
	@GetMapping(value = "/{id}")
    public DoctorDTO findById(@PathVariable("id") Integer id){
        return doctorService.finddoctorById(id);
    }
	


    @PostMapping()
    public DoctorDTO insertdoctorDTO(@RequestBody DoctorDTO doctorDTO){
        return doctorService.insert(doctorDTO);
    }

    @PutMapping()
    public DoctorDTO updatedoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.update(doctorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        doctorService.delete(id);
    }
}