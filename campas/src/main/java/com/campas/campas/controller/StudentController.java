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
import com.campas.campas.entity.Student;
import com.campas.campas.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//GET ALL
	@GetMapping
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	//GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		Student Student = studentService.getStudentById(id);
		
		if(Student != null) {
			return ResponseEntity.ok(Student);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//CREATE
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody StudentDTO dto){
		Student Student = new Student();
		Student.setName(dto.getName());
		Student.setEmail(dto.getEmail());
		
		Student savedStudent = studentService.saveStudent(Student);
		return ResponseEntity.ok(savedStudent);
	}
	
	//UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStuent(@PathVariable Long id,@RequestBody StudentDTO dto){
		
		Student existing = studentService.getStudentById(id);
		
		if(existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		
		Student updated = studentService.saveStudent(existing);
		return ResponseEntity.ok(updated);
		
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		
		Student existing = studentService.getStudentById(id);
		
		if(existing == null) {
			return ResponseEntity.notFound().build();
		}
		
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
	
	//SEARCH BY EMAIL(RequestParam)
	@GetMapping("/search")
	public ResponseEntity<Student> getByEmail(@RequestParam String email){
		Student Student = studentService.getByEmail(email);
		
		if(Student == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(Student);
	}
	
	//SEARCH BY NAME(Derived Query)
	@GetMapping("/searchByName")
	public List<Student> searchByName(@RequestParam String keyword){
		return  studentService.searchByName(keyword);
		
	}

}
