package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.swp.ArtQuack.Enum.CourseStatus;
import com.swp.ArtQuack.exception.BadRequest;
import com.swp.ArtQuack.service.EmailService;
import com.swp.ArtQuack.view.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.service.CourseService;
import com.swp.ArtQuack.service.EnrollmentService;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.view.EnrollmentObject;




@RestController
@RequestMapping("/api")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private LearnerService learnerService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private EmailService emailService;

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
	
//	@GetMapping("/enrollment/learner/{learnerID}")
//	public ResponseEntity<List<EnrollmentObject>> findByStudentID(@PathVariable("learnerID") int learnerID) {
//		List<Enrollment> ls = enrollmentService.findByLearnerID(learnerID);
//		List<EnrollmentObject> list = enrollmentService.display(ls);
//		return ResponseEntity.status(HttpStatus.OK).body(list);
//	}

	@GetMapping("/enrollment/learner/{learnerID}")
	public ResponseEntity<List<EnrollmentObject>> getEnrollmentsByLearnerAndStatus(@PathVariable("learnerID") int learnerID) {
		List<Enrollment> enrollments = enrollmentService.findByLearnerID(learnerID);
		List<EnrollmentObject> enrollmentObjects = enrollmentService.display(enrollments);
		return ResponseEntity.ok(enrollmentObjects);
	}
	
	@GetMapping("/enrollment/course/{courseID}")
	public ResponseEntity<List<EnrollmentObject>> findByCourseID(@PathVariable("courseID") int courseID) {		
		List<Enrollment> ls = enrollmentService.findByCourseID(courseID);
		List<EnrollmentObject> list = enrollmentService.display(ls);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

//	@GetMapping("/course/{courseID}/learner/{learnerID}")
//	public ResponseEntity<Enrollment> checkEnroll(@PathVariable("courseID") int courseID, @PathVariable("learnerID") int learnerID, @RequestBody Enrollment enrollment){
//		Learner learner = learnerService.findById(learnerID);
//		if(learner == null) return ResponseEntity.notFound().header("message", "Learner not found. Adding failed").build();
//
//		Course course = courseService.findById(courseID, );
//		if(course == null) return ResponseEntity.notFound().header("message", "Course not found. Adding failed").build();
//
//		if(enrollmentService.findById(enrollment.getEnrollmentID()) != null)
//			return ResponseEntity.badRequest().header("message", "Enrollment with such ID already exists").build();
//
//			boolean hasEnrolled = enrollmentService.hasEnrolled(learnerID, courseID);
//			if (hasEnrolled)
//				return ResponseEntity.badRequest().header("message","Learner has already enrolled in the course!").build();
//
//		return ResponseEntity.badRequest().header("message","Learner has already enrolled in the course!").build();
//	}
	
	@GetMapping("enrollment/course/{courseID}/learner/{learnerID}")
	public ResponseEntity<List<EnrollmentObject>> findByCourseIDAndLearnerID(@PathVariable("courseID") int courseID, @PathVariable("learnerID") int learnerID) {		
		List<Enrollment> ls = enrollmentService.findByCourseIDAndLearnerID(courseID, learnerID);
		List<EnrollmentObject> list = enrollmentService.display(ls);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//ADD
	@PostMapping("/learner/{learnerID}/course/{courseID}/enrollment")
	public ResponseEntity<Enrollment> createEnrollment(@PathVariable("learnerID") int learnerID,@PathVariable("courseID") int courseID, @RequestBody Enrollment enrollment){
		try {
			
			Learner learner = learnerService.findById(learnerID);
			if(learner == null) return ResponseEntity.notFound().header("message", "Learner not found. Adding failed").build();
			
			Course course = courseService.findById(courseID);
			if(course == null) return ResponseEntity.notFound().header("message", "Course not found. Adding failed").build();

			if(enrollmentService.findById(enrollment.getEnrollmentID()) != null) 
				return ResponseEntity.badRequest().header("message", "Enrollment with such ID already exists").build();
			
			boolean hasEnrolled = enrollmentService.hasEnrolled(learnerID, courseID);
		        if (hasEnrolled)
		            throw new BadRequest("Learner has already enrolled in the course!");
						
			enrollment.setLearner(learner);
			enrollment.setCourse(course);
			enrollment.setStatus(true);
			Enrollment savedEnroll = enrollmentService.add(enrollment);
			if(savedEnroll != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedEnroll);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			throw new BadRequest(e.getMessage());
		}
	}
	
	@PutMapping("/enrollment/{enrollmentID}/update")
	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("enrollmentID") int enrollmentID , @RequestBody Enrollment enrollment){

		Enrollment available = enrollmentService.findById(enrollment.getEnrollmentID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Enrollment found for such ID").build();
		enrollment.setStatus(true);
		Enrollment updatedEnroll = enrollmentService.update(enrollment);
		if(updatedEnroll != null)
			return ResponseEntity.ok(updatedEnroll);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@PutMapping("/enrollment/{enrollmentID}/update-of-report")
	public ResponseEntity<Enrollment> updateEnrollmentForReport(@PathVariable("enrollmentID") int enrollmentID , @RequestBody Enrollment enrollment){
		Enrollment available = enrollmentService.findById(enrollment.getEnrollmentID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Enrollment found for such ID").build();
		available.setTypeOfReport(enrollment.getTypeOfReport());
		available.setReport(enrollment.getReport());
		Enrollment updatedEnroll = enrollmentService.update(available);
		EmailDetail emailDetail = new EmailDetail();
		emailDetail.setRecipient(updatedEnroll.getCourse().getInstructor().getEmail());
		emailDetail.setToName(updatedEnroll.getCourse().getInstructor().getName());
		emailDetail.setCourseName(updatedEnroll.getCourse().getName());
		emailDetail.setSubject("Report for course");
		emailDetail.setMsgBody("aaa");
		emailService.sendMailTemplate(emailDetail, "report-to-instructor");
		if(updatedEnroll != null)
			return ResponseEntity.ok(updatedEnroll);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}
	//Report
//	@PutMapping("enrollment/{enrollmentID}/type-of-report/{typeOfReport}/report/{report}")
//	public ResponseEntity<Enrollment> updateEnrollmentForReport(@PathVariable("enrollmentID") int enrollmentID,@PathVariable("typeOfReport") String typeOfReport,@PathVariable("report") String report , @RequestBody Enrollment enrollment){
//		Enrollment available = enrollmentService.findById(enrollment.getEnrollmentID());
//		if(available == null)
//			return  ResponseEntity.notFound().header("message", "No Enrollment found for such ID").build();
//		available.setTypeOfReport(typeOfReport);
//		available.setReport(report);
//		available.setStatus(true);
//		Enrollment updatedEnroll = enrollmentService.update(available);
//		if(updatedEnroll != null)
//			return ResponseEntity.ok(updatedEnroll);
//		else
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//
//	}
	
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
