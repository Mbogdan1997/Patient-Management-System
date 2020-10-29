package com.utcn.hospital.soa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.utcn.hospital.SpringConfiguration;
import com.utcn.hospital.dto.ActivityDTO;
import com.utcn.hospital.dto.ChartItemDTO;
import com.utcn.hospital.mapper.ActivityChartItemMapper;
import com.utcn.hospital.repository.ActivityRepository;

@WebService(endpointInterface = "com.utcn.hospital.soa.ActivityService")
public class ActivityServiceImpl implements ActivityService {
	
	private ActivityRepository activityRepository=(ActivityRepository) SpringConfiguration.contextProvider()
	.getApplicationContext().getBean("activityRepository");
	
	private ActivityChartItemMapper activityChartItemMapper=(ActivityChartItemMapper) SpringConfiguration.contextProvider()
	.getApplicationContext().getBean("activityChartItemMapper");
	
	private com.utcn.hospital.service.ActivityService activityService=(com.utcn.hospital.service.ActivityService) SpringConfiguration.contextProvider()
			.getApplicationContext().getBean("activityService");

	@Override
	public List<ChartItemDTO> viewActivityForPatient(int patientId,String date) {
		// TODO Auto-generated method stub

		SimpleDateFormat sdmDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date2=null;
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
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH)+1;
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		return activityChartItemMapper.toDTOs(activityRepository.viewActivityForPatient(patientId,day,month,year));	
	}
	
	public List<ActivityDTO> viewNormalActivityForPatient(@PathVariable int id){
		return activityService.viewNormalActivities(id);
	}
	
	public List<ActivityDTO> viewUnnormalActivityForPatient(@PathVariable int id){
		return activityService.viewUnnormalActivities(id);
	}


}
