package com.utcn.hospital.dto;

import java.util.ArrayList;
import java.util.List;

public class ChartDTO {
	private List<String> activityNames;
	private List<Integer> activityPeriods;
	
	public ChartDTO() {
		activityNames=new ArrayList<String>();
		activityPeriods=new ArrayList<Integer>();
	}
	
	public void add(String name,Integer period) {
		activityNames.add(name);
		activityPeriods.add(period);
	}
	
	public void modify(int index,int newPeriod) {
		Integer sum=activityPeriods.get(index)+newPeriod;
		activityPeriods.remove(index);
		activityPeriods.add(index,sum);
	}

	public List<String> getActivityNames() {
		return activityNames;
	}

	public void setActivityNames(List<String> activityNames) {
		this.activityNames = activityNames;
	}

	public List<Integer> getActivityPeriods() {
		return activityPeriods;
	}

	public void setActivityPeriods(List<Integer> activityPeriods) {
		this.activityPeriods = activityPeriods;
	}
	
	
	
	

}
