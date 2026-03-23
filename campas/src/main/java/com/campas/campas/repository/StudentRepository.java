package com.campas.campas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campas.campas.entity.student;

@Repository
public interface StudentRepository extends JpaRepository<student, Long>{
	
	//Derived Query Method
	student findByEmail(String email);
	
	//Derived Query Method with LIKE
	List<student> findByNameContaining(String keyword);

}
