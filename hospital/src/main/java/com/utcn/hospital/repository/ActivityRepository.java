package com.utcn.hospital.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Activity;
import com.utcn.hospital.entity.IsTaken;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	@Query("Select a from Activity a where a.patient.id=:patientId and DAY(a.startDate)=:day and YEAR(a.startDate)=:year and MONTH(a.startDate) =:month order by a.name")
	public List<Activity> viewActivityForPatient(int patientId,int day,int month,int year);
	
	@Query("Select  Distinct a.startDate from Activity a where a.patient.id =:patientId order by a.startDate")
	public List<String> viewActivityDayForPatient(int patientId);
	
	@Query("Select a from Activity a where a.normal=true and a.patient.id=:patientId")
	public List<Activity> viewNormalActivity(int patientId);
	
	@Query("Select a from Activity a where a.normal=false and a.patient.id=:patientId")
	public List<Activity> viewUnnormalActivity(int patientId);

	
	
	
	
	

}
