package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.ChartItemDTO;
import com.utcn.hospital.entity.Activity;

@Component
public class ActivityChartItemMapper  implements ListMapper<Activity, ChartItemDTO>{

	@Override
	public List<ChartItemDTO> toDTOs(List<Activity> entities) {
		// TODO Auto-generated method stub
		List<ChartItemDTO> chartItemDTOs=new ArrayList<ChartItemDTO>();
		
		for(Activity activity:entities) {
			ChartItemDTO chartItemDTO=new ChartItemDTO();
			chartItemDTO.setActivityName(activity.getName());
			DateTime start = new DateTime(activity.getStartDate());
			DateTime end = new DateTime(activity.getEndDate());
			Period period = new Period(start, end);
			int duration=period.getHours()*60+period.getMinutes();
			chartItemDTO.setPeriodInMinutes(duration);
			chartItemDTOs.add(chartItemDTO);
		}
		
		return chartItemDTOs;
	}

	@Override
	public List<Activity> toEntities(List<ChartItemDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
