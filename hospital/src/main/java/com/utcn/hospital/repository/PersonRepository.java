package com.utcn.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
