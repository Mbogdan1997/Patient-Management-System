package com.utcn.hospital.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityNotFoundException;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.ActivityDTO;
import com.utcn.hospital.dto.ChartDTO;
import com.utcn.hospital.dto.ChartItemDTO;
import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.entity.Activity;
import com.utcn.hospital.mapper.ActivityChartItemMapper;
import com.utcn.hospital.mapper.ActivityDTOMapper;
import com.utcn.hospital.repository.ActivityRepository;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityDTOMapper activityDTOMapper;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityChartItemMapper activityChartItemMapper;

	public ChartDTO viewActivityForPatient(int patientId, String date) {

		SimpleDateFormat sdmDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = sdmDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Romania"));
		cal.setTime(date2);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH) + 1;
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		List<Activity> activities=activityRepository.viewActivityForPatient(patientId, day, month, year);
		ChartDTO chartDTO=new ChartDTO();
		int index=0;
		
		for(Activity activity:activities) {
			
			DateTime start = new DateTime(activity.getStartDate());
			DateTime end = new DateTime(activity.getEndDate());
			Period period = new Period(start, end);
			int duration=period.getHours()*3600+period.getMinutes()*60+period.getSeconds();
			
			if(index==0 || !chartDTO.getActivityNames().get(index-1).equals(activity.getName()) ) {
				chartDTO.add(activity.getName(), duration);
				index++;
			}
			else {
				chartDTO.modify(index-1,duration );
			}
			
		}
		return chartDTO;
		
	}

	public List<DateDTO> viewAllDayForPatient(int patientId) {
		int i = 0;
		List<String> dates = activityRepository.viewActivityDayForPatient(patientId);
		List<DateDTO> dateDTOs = new ArrayList<DateDTO>();
		for (String date : dates) {
			String newDateString[] = date.split(" ");
			if (dateDTOs.isEmpty() || !dateDTOs.get(i - 1).getDate().equals(newDateString[0])) {
				DateDTO dateDTO = new DateDTO();
				dateDTO.setDate(newDateString[0]);
				dateDTOs.add(dateDTO);
				i++;
			}
		}
		return dateDTOs;
	}
	
	public List<ActivityDTO> viewNormalActivities(int patientId){
		return activityDTOMapper.toDTOs(activityRepository.viewNormalActivity(patientId));
	}
	
	public List<ActivityDTO> viewUnnormalActivities(int patientId){
		return activityDTOMapper.toDTOs(activityRepository.viewUnnormalActivity(patientId));
	}
	
	public void makeRecomandation(ActivityDTO activityDTO) {
		Activity activity=activityRepository.findById(activityDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Cannot find car with ID: " + activityDTO.getId()));
		activity.setRecomandation(activityDTO.getRecomandation());
		activityRepository.save(activity);
	}
	
	

}
