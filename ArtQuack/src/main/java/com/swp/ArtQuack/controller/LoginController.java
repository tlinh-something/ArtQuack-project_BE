package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.Admin;
import com.swp.ArtQuack.exception.BadRequest;
import com.swp.ArtQuack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/login/email/{email}/password/{password}/role/{role}")
	public ResponseEntity<Object> login(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("role") String role){
		if(role.equalsIgnoreCase("learner")){
			Learner student = studentService.login(email, password);
			if(student == null){
				throw new BadRequest("Invalid learner!");
			}else if (!student.isStatus()) {
	            throw new BadRequest("Account not found!");
	        }
			return ResponseEntity.ok(student);
		}else if(role.equalsIgnoreCase("instructor")) {
			Instructor instructor = instructorService.login(email, password);
			if(instructor == null){
				throw new BadRequest("Invalid instructor!");
			}else if (!instructor.isStatus()) {
	            throw new BadRequest("Account not found!");
	        }
			return ResponseEntity.ok(instructor);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/login")
	public ResponseEntity<Object> login2(@RequestParam("email") String email, @RequestParam("password") String password) {
		// Check if the user is a Learner
		Learner student = studentService.login(email, password);
		if (student != null && student.isStatus()) {
			return ResponseEntity.ok(student);
		}

		// Check if the user is an Instructor
		Instructor instructor = instructorService.login(email, password);
		if (instructor != null && instructor.isStatus()) {
			return ResponseEntity.ok(instructor);
		}

		// Check if the user is an Admin
		Admin admin = adminService.login(email, password);
		if (admin != null) {
			return ResponseEntity.ok(admin);
		}

		throw new BadRequest("Invalid email or password");
	}
}
