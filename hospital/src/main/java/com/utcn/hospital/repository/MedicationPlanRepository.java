package com.utcn.hospital.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.dto.DateDTO;
import com.utcn.hospital.entity.MedicationPlan;


@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {
	
	
	
	@Query("Select mp from MedicationPlan mp where mp.doctor.id=:doctorId")
	public List<MedicationPlan> findByDoctorId(int doctorId);
	@Query("Select mp from MedicationPlan mp where mp.patient.id=:patientId")
	public List<MedicationPlan> findByPatientId(int patientId);
	@Query("Select MIN(mp.startDate) from MedicationPlan mp where mp.patient.id=:patientId")
	public Date findMinDate(int patientId);
	@Query("Select Max(mp.finishDate) from MedicationPlan mp where mp.patient.id=:patientId")
	public Date findMaxDate(int patientId);
	


}
