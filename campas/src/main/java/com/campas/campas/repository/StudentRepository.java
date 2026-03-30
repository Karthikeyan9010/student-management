package com.campas.campas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campas.campas.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	//Derived Query Method
	Student findByEmail(String email);
	
	//Derived Query Method with LIKE
	List<Student> findByNameContaining(String keyword);

}
