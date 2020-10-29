package com.utcn.hospital.soa;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.utcn.hospital.dto.ActivityDTO;
import com.utcn.hospital.dto.ChartItemDTO;

@WebService
public interface ActivityService {
	
	@WebMethod
	List<ChartItemDTO> viewActivityForPatient(int patientId,String date);
	
	@WebMethod
	public List<ActivityDTO> viewNormalActivityForPatient(@PathVariable int id);
	
	@WebMethod
	public List<ActivityDTO> viewUnnormalActivityForPatient(@PathVariable int id);
}
