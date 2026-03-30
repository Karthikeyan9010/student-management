package com.campas.campas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campas.campas.entity.Course;
import com.campas.campas.repository.CourseRepository;

@Service 
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

}
