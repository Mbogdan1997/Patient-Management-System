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

import com.utcn.hospital.dto.DrugDTO;
import com.utcn.hospital.dto.MedicationPlanDTO;
import com.utcn.hospital.service.DrugService;

@RestController
@CrossOrigin
@RequestMapping(value = "/drug",produces = {MediaType.APPLICATION_JSON_VALUE})
public class DrugRestController {
	
public DrugService drugService;
	
	@Autowired
	public DrugRestController(DrugService drugService) {
		
		// TODO Auto-generated constructor stub
		this.drugService=drugService;
	}
	
	@GetMapping()
	public List<DrugDTO> viewAll(){
		return drugService.viewAll();
		
	}
	
	@GetMapping(value = "/{id}")
    public DrugDTO findById(@PathVariable("id") Integer id){
        return drugService.finddrugById(id);
    }
	


    @PostMapping()
    public DrugDTO insertdrugDTO(@RequestBody DrugDTO drugDTO){
        return drugService.insert(drugDTO);
    }

    @PutMapping()
    public DrugDTO updatedrug(@RequestBody DrugDTO drugDTO) {
        return drugService.update(drugDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        drugService.delete(id);
    }
    
    @GetMapping("/medicalplan/{id}")
    public List<DrugDTO> findDrugInMedicalPlan(@PathVariable("id") int id){
    	return drugService.findDrugInMedicationPlan(id);
    
    }
    
    @CrossOrigin
    @GetMapping("/medicalplan/not/{id}")
    public List<DrugDTO> findDrugNotInMedicalPlan(@PathVariable("id") int id){
    	System.out.println("Planul medical este"+id);
    	return drugService.findDrugNotInMedicalPlan(id);
    
    }
    

}
