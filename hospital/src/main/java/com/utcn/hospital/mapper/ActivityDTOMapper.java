package com.utcn.hospital.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.ActivityDTO;
import com.utcn.hospital.entity.Activity;

@Component
public class ActivityDTOMapper implements ListMapper<Activity, ActivityDTO> {

	@Override
	public List<ActivityDTO> toDTOs(List<Activity> entities) {
		// TODO Auto-generated method stub
		List<ActivityDTO> activityDTOs=new ArrayList<ActivityDTO>();
		for(Activity activity:entities) {
			ActivityDTO activityDTO=new ActivityDTO();
			activityDTO.setId(activity.getId());
			activityDTO.setStartDate(activity.getStartDate());
			activityDTO.setEndDate(activity.getEndDate());
			activityDTO.setName(activity.getName());
			activityDTO.setNormal(activity.isNormal());
			activityDTO.setRecomandation(activity.getRecomandation());
			activityDTOs.add(activityDTO);
		}
		return activityDTOs;
	}

	@Override
	public List<Activity> toEntities(List<ActivityDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
