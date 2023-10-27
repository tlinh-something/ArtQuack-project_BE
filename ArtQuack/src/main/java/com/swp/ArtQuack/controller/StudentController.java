package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Student;
import com.swp.ArtQuack.service.StudentService;
//import com.swp.ArtQuack.view.StudentObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
//	@GetMapping("/students")
//	public ResponseEntity<List<StudentObject>> retrieveAllStudents(){
//		List<Student> ls = studentService.findAll();
//		List<StudentObject> list = studentService.display(ls);
//		return ResponseEntity.ok(list);
//    }
//	
//	@GetMapping("/student/{studentID}")
//	public ResponseEntity<StudentObject> retrieveStudent(@PathVariable int studentID) {
//		Student student = studentService.findById(studentID);
//		if(student != null) {
//			List<Student> ls = new ArrayList<>();
//			ls.add(student);
//			List<StudentObject> list = studentService.display(ls);
//			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
//		}else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	@GetMapping("/students/{name}")
//	public ResponseEntity<List<StudentObject>> retrieveStudentsByName(@PathVariable String name) {
//		List<Student> ls = studentService.findByNameIgnorecase(name);
//		if(ls.size() > 0) {
//			List<StudentObject> list = studentService.display(ls);
//			return ResponseEntity.status(HttpStatus.OK).body(list);
//		}else
//			return ResponseEntity.notFound().build();
//	}
//	
//	@PostMapping("/createstudent")
//	public ResponseEntity<Student> createStudent(@RequestBody Student student){
//			if(studentService.findById(student.getStudentID()) != null)
//				return ResponseEntity.badRequest().header("message", "Student with such ID already exists").build();
//			
//			Student savedStudent = studentService.add(student);
//			return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
//	}
	
//	@PostMapping("/createstudent")
//	public ResponseEntity<Student> createStudent(@RequestBody Student student){
//		Student savedStudent = studentService.add(student);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
//	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> retrieveAllStudents(){
		return ResponseEntity.ok(studentService.findAll());
    }		
	
	@GetMapping("/students/{keyword}")
	public ResponseEntity<List<Student>> retrieveStudentsByKeyword(@PathVariable String keyword){
		return ResponseEntity.ok(studentService.findByNameIgnorecase(keyword));
    }
	@GetMapping("/student/{studentID}")
	public ResponseEntity<Student> retrieveStudent(@PathVariable int studentID) {
		Student student = studentService.findById(studentID);
		if(student != null) {
			return ResponseEntity.status(HttpStatus.OK).body(student);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/createstudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		try {
			if(studentService.findById(student.getStudentID()) != null)
				return ResponseEntity.notFound().header("message", "Student with such ID already exists").build();
			
			Student savedStudent = studentService.add(student);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new student").build();
		}
	}
}
