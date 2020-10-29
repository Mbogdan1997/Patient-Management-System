package com.utcn.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Caregiver;
import com.utcn.hospital.entity.Patient;


@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {
	@Query("Select c from Caregiver c where c.username=:username")
	public Caregiver findByUsername(String username);
}
