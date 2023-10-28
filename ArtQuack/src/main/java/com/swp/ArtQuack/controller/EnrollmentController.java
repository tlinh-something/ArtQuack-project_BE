package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.entity.Student;
import com.swp.ArtQuack.service.CourseService;
import com.swp.ArtQuack.service.EnrollmentService;
import com.swp.ArtQuack.service.StudentService;
import com.swp.ArtQuack.view.EnrollmentObject;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/enrollments")
	public ResponseEntity<List<EnrollmentObject>> retreiveAllEnrollments() {
		List<Enrollment> ls = enrollmentService.findAll();
		List<EnrollmentObject> list = enrollmentService.display(ls);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/enrollment/{enrollmentID}")
	public ResponseEntity<EnrollmentObject> retrieveEnrollment(@PathVariable int enrollmentID) {
		Enrollment enroll = enrollmentService.findById(enrollmentID);
		if(enroll != null) {
			List<Enrollment> ls = new ArrayList<>();
			ls.add(enroll);
			List<EnrollmentObject> list = enrollmentService.display(ls);
			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/enrollment/student/{studentID}")
	public ResponseEntity<List<EnrollmentObject>> findByStudentID(@PathVariable("studentID") int studentID) {		
		List<Enrollment> ls = enrollmentService.findByStudentID(studentID);
		List<EnrollmentObject> list = enrollmentService.display(ls);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/enrollment/course/{courseID}")
	public ResponseEntity<List<EnrollmentObject>> findByCourseID(@PathVariable("courseID") int courseID) {		
		List<Enrollment> ls = enrollmentService.findByStudentID(courseID);
		List<EnrollmentObject> list = enrollmentService.display(ls);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//ADD
	@PostMapping("/student/{studentID}/course/{courseID}/enrollment")
	public ResponseEntity<Enrollment> createEnrollment(@PathVariable("studentID") int studentID,@PathVariable("courseID") int courseID, @RequestBody Enrollment enrollment){
		try {
			Student student = studentService.findById(studentID);
			if(student == null) return ResponseEntity.notFound().header("message", "Student not found. Adding failed").build();
			
			Course course = courseService.findById(courseID);
			if(course == null) return ResponseEntity.notFound().header("message", "Course not found. Adding failed").build();
			
			if(enrollmentService.findById(enrollment.getEnrollmentID()) != null) 
				return ResponseEntity.badRequest().header("message", "Enrollment with such ID already exists").build();
			
			enrollment.setStudent(student);
			enrollment.setCourse(course);
			enrollment.setStatus(true);
			Enrollment savedEnroll = enrollmentService.add(enrollment);
			if(savedEnroll != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedEnroll);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new enrollment").build();
		}
	}
	
	//DELETE
	@DeleteMapping("/enrollment/{enrollmentID}")
	public ResponseEntity<Void> deleteEnrollment(@PathVariable int enrollmentID){
		try{
			enrollmentService.delete(enrollmentID);
			return ResponseEntity.noContent().header("message", "Enrollment deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Enrollment deletion failed").build();
		}
	}
}
