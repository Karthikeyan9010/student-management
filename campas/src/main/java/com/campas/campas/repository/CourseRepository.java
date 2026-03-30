package com.campas.campas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campas.campas.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	

}
