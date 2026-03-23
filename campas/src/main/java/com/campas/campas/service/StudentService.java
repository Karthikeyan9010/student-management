package com.campas.campas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campas.campas.entity.student;
import com.campas.campas.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository StudentRepository;
	
	public List<student> getAllStudents(){
		return StudentRepository.findAll();
	}
	
	public student saveStudent(student Student) {
		return StudentRepository.save(Student);
	}
	
	public student getStudentById(Long id) {
		return StudentRepository.findById(id).orElse(null);
	}
	
	public void deleteStudent(Long id) {
		StudentRepository.deleteById(id);
	}
	
	public student getByEmail(String email) {
		return StudentRepository.findByEmail(email);
	}
	
	public List<student> searchByName(String keyword){
		return StudentRepository.findByNameContaining(keyword);
	}

}
