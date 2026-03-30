package com.campas.campas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campas.campas.entity.Student;
import com.campas.campas.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student saveStudent(Student Student) {
		return studentRepository.save(Student);
	}
	
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
	public Student getByEmail(String email) {
		return studentRepository.findByEmail(email);
	}
	
	public List<Student> searchByName(String keyword){
		return studentRepository.findByNameContaining(keyword);
	}

}
