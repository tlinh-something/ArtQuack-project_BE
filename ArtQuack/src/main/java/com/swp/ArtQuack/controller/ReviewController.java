package com.swp.ArtQuack.controller;

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

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Review;
import com.swp.ArtQuack.entity.Student;
import com.swp.ArtQuack.service.CourseService;
import com.swp.ArtQuack.service.ReviewService;
import com.swp.ArtQuack.service.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/reviews")
	public List<Review> findAll(){
		return reviewService.findAll();
	}
	
	@GetMapping("/review/{reviewID}")
	public Review findById(int reviewID) {
		return reviewService.findById(reviewID);
	}
	
	@PostMapping("/courseID/{courseID}/student/{studentID}/review")
	public ResponseEntity<Review> createReview(@PathVariable("studentID") int studentID,@PathVariable("courseID") int courseID, @RequestBody Review review){
		try {
			Student student = studentService.findById(studentID);
			if(student == null) return ResponseEntity.notFound().header("message", "Student not found. Adding failed").build();
			
			Course course = courseService.findById(courseID);
			if(course == null) return ResponseEntity.notFound().header("message", "Course not found. Adding failed").build();
			
			if(reviewService.findById(review.getReviewID()) != null) 
				return ResponseEntity.badRequest().header("message", "Review with such ID already exists").build();
			
			review.setStudent(student);
			review.setCourse(course);
			review.setStatus(true);
			Review savedReview = reviewService.add(review);
			if(savedReview != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new enrollment").build();
		}
	}
	
	@DeleteMapping("/review/{reviewID}")
	public ResponseEntity<Void> deleteReview(@PathVariable int reviewID){
		try{
			reviewService.delete(reviewID);
			return ResponseEntity.noContent().header("message", "Enrollment deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Enrollment deletion failed").build();
		}
	}
}
