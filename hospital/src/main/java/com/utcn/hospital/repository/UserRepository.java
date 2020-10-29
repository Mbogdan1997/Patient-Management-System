package com.utcn.hospital.repository;

import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utcn.hospital.entity.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Integer>{

	@Query("Select u from User u where u.username=:username")
    public User findByUsername(String username);

}
