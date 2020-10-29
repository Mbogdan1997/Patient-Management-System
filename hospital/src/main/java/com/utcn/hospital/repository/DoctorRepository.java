package com.utcn.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Doctor;
import com.utcn.hospital.entity.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query("Select d from Doctor d where d.username=:username")
	public Patient findByUsername(String username);
}
