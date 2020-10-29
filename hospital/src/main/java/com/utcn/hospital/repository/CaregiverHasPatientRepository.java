package com.utcn.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.CaregiverHasPatient;
import com.utcn.hospital.entity.CaregiverHasPatientKey;

@Repository
public interface CaregiverHasPatientRepository extends JpaRepository<CaregiverHasPatient, CaregiverHasPatientKey> {

	@Query("Select c from CaregiverHasPatient c where c.caregiver.id=:id")
	List<CaregiverHasPatient> findByCaregiverId(int id);
	
	
	@Query("Select c from CaregiverHasPatient c where c.patient.id=:id ")
	List<CaregiverHasPatient> findByPatientId(int id);
	
	@Query("Select c from CaregiverHasPatient c where c.patient.id=:patientId and c.caregiver.id=:caregiverId")
	CaregiverHasPatient findCaregiverHasPatient(int caregiverId,int patientId);
		
}
