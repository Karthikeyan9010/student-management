package com.campas.campas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campas.campas.dto.StudentDTO;
import com.campas.campas.entity.student;
import com.campas.campas.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//GET ALL
	@GetMapping
	public List<student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	//GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<student> getStudentById(@PathVariable Long id){
		student Student = studentService.getStudentById(id);
		
		if(Student != null) {
			return ResponseEntity.ok(Student);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//CREATE
	@PostMapping
	public ResponseEntity<student> createStudent(@RequestBody StudentDTO dto){
		student Student = new student();
		Student.setName(dto.getName());
		Student.setemail(dto.getEmail());
		
		student savedStudent = studentService.saveStudent(Student);
		return ResponseEntity.ok(savedStudent);
	}
	
	//UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<student> updateStuent(@PathVariable Long id,@RequestBody StudentDTO dto){
		
		student existing = studentService.getStudentById(id);
		
		if(existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		existing.setName(dto.getName());
		existing.setemail(dto.getEmail());
		
		student updated = studentService.saveStudent(existing);
		return ResponseEntity.ok(updated);
		
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		
		student existing = studentService.getStudentById(id);
		
		if(existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
	
	//SEARCH BY EMAIL(RequestParam)
	@GetMapping("/search")
	public ResponseEntity<student> getByEmail(@RequestParam String email){
		student Student = studentService.getByEmail(email);
		
		if(Student == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(Student);
	}
	
	//SEARCH BY NAME(Derived Query)
	@GetMapping("/searchByName")
	public List<student> searchByName(@RequestParam String keyword){
		return  studentService.searchByName(keyword);
		
	}

}
