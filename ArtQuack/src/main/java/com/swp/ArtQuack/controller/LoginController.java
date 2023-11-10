package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.service.InstructorService;
import com.swp.ArtQuack.service.LearnerService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private LearnerService studentService;
	
	@GetMapping("/login/email/{email}/password/{password}/role/{role}")
	public ResponseEntity<Object> login(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("role") String role){
		if(role.equalsIgnoreCase("learner")){
			Learner student = studentService.login(email, password);
			if(student == null){
				throw new BadRequest("Invalid learner!");
			}else if (!student.isStatus()) {
	            throw new BadRequest("Account has been deleted!");
	        }
			return ResponseEntity.ok(student);
		}else if(role.equalsIgnoreCase("instructor")) {
			Instructor instructor = instructorService.login(email, password);
			if(instructor == null){
				throw new BadRequest("Invalid instructor!");
			}else if (!instructor.isStatus()) {
	            throw new BadRequest("Account has been deleted!");
	        }
			return ResponseEntity.ok(instructor);
		}
		return ResponseEntity.notFound().build();
	}
}
