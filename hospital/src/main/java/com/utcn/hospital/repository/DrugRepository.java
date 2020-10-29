package com.utcn.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Drug;


@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer>{
	
	@Query("Select d from Drug d where d.name=:name")
	Drug findByName(String name);
	
	@Query("Select d from Drug d where d.id not in (Select d1.id from Drug d1 inner join IntakeInterval ii on d1.id=ii.drug.id where ii.medicationPlan.id=:medicalPlanId)")
	List<Drug> findDrugsNotInMedicalPlan(int medicalPlanId);
	
	@Query("Select d from Drug d inner join IntakeInterval ii on d.id=ii.drug.id where ii.medicationPlan.id=:medicalPlanId")
	List<Drug> findDrugsInMedicalPlan(int medicalPlanId);
	
	
	

}
