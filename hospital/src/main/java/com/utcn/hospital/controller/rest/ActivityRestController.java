package com.utcn.hospital.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.utcn.hospital.dto.ActivityDTO;
import com.utcn.hospital.dto.ChartDTO;
import com.utcn.hospital.dto.ChartItemDTO;
import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.repository.ActivityRepository;
import com.utcn.hospital.service.ActivityService;

@RestController
@CrossOrigin
@RequestMapping(value = "/activity",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ActivityRestController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostMapping("/{id}")
	public ChartDTO viewActivityForPatient(@PathVariable int id,@RequestBody DateDTO dateDTO){
//		final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss";
//		final String NEW_FORMAT = "yyyy-MM-dd";
//		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

//		Date d;
//		try {
//			d = sdf.parse(dateDTO.getDate().toString());
//			sdf.applyPattern(NEW_FORMAT);
//			String newDateString = sdf.format(d);
//			System.out.println(newDateString);
			return activityService.viewActivityForPatient(id, dateDTO.getDate());
		
	
	}
	
	@GetMapping("/day/{id}")
	public List<DateDTO> viewAllDatesForPacient(@PathVariable int id){
		return activityService.viewAllDayForPatient(id);
	}
	
	@GetMapping("/normalActivity/{id}")
	public List<ActivityDTO> viewNormalActivityForPatient(@PathVariable int id){
		return activityService.viewNormalActivities(id);
	}
	
	@GetMapping("/unnormalActivity/{id}")
	public List<ActivityDTO> viewUnnormalActivityForPatient(@PathVariable int id){
		return activityService.viewUnnormalActivities(id);
	}
	
	@PostMapping("/makerecomandation")
	public void makeRecomandation(ActivityDTO activityDTO) {
		activityService.makeRecomandation(activityDTO);
	}
	

}
