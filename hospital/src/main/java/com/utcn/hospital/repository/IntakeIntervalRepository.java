package com.utcn.hospital.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.IntakeInterval;
import com.utcn.hospital.entity.IntakeIntervalKey;
import com.utcn.hospital.entity.MedicationPlan;


@Repository
public interface IntakeIntervalRepository extends JpaRepository<IntakeInterval, IntakeIntervalKey>{

	@Query("Select ii from IntakeInterval ii where ii.medicationPlan.id=:id")
	List<IntakeInterval> findByMadicationPlanId(int id);
	
	@Query("Select ii from IntakeInterval ii where ii.drug.id=:id")
	List<IntakeInterval> findByDrugId(int id);
	
	
	@Query("Select ii from IntakeInterval ii inner join MedicationPlan mp on ii.medicationPlan.id=mp.id"
			+ " inner join Patient p on mp.patient.id=p.id where :date between mp.startDate and mp.finishDate "
			+ "and p.id=:userId")
	List<IntakeInterval> findBeetweenIntervalForUser(Date date,int userId);
	
	
}
