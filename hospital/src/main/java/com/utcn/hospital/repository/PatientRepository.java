package com.utcn.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	
	@Query("Select p from Patient p where p.username=:username")
	public Patient findByUsername(String username);
	
	
	@Query("Select p from Patient p where p.id not in (Select p.id from Patient p inner join CaregiverHasPatient chp on chp.patient.id=p.id where chp.caregiver.id=:id)")
	public List<Patient> patientWithoutCaregiver(int id);

}
