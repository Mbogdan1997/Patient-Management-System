package com.utcn.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Activity;
import com.utcn.hospital.entity.IsTaken;

@Repository
public interface IsTakenRepository extends JpaRepository<IsTaken, Integer> {
	
	@Query("Select it from IsTaken it where DAY(it.date)=:day and YEAR(it.date)=:year and MONTH(it.date) =:month "
			+ "and it.intakeInterval.medicationPlan.patient.id=:patientId"
			+ "")
	public List<IsTaken> viewMedicationPlanStatus(int day,int month,int year,int patientId);
	
	

}
