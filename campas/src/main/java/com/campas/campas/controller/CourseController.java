package com.campas.campas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.campas.campas.dto.CourseDTO;
import com.campas.campas.entity.Course;
import com.campas.campas.entity.Student;
import com.campas.campas.service.CourseService;
import com.campas.campas.service.StudentService;

@RestController
@RequestMapping("/courses")
public class CourseController {

   
	@Autowired
    private CourseService courseService;

    @Autowired   
    private StudentService studentService;

     //ADD COURSE
    @PostMapping
    public Course addCourse(@RequestBody CourseDTO dto) {
    	
    	System.out.println("API Called");

        Student s = studentService.getStudentById(dto.getStudentId());

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setStudent(s);  

        return courseService.saveCourse(course);
        
    }
    
 
    
	
		
//		  @PostMapping  public Course addCourse(@RequestBody Course course) { 
//		 return courseService.saveCourse(course);
//		  }
		


    @GetMapping
    public List<Course> getAllCourse(){
    	return courseService.getAllCourses();
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Deleted Successfully";
    }
}