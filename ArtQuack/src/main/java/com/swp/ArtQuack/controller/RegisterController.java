
package com.swp.ArtQuack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.service.InstructorService;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.view.Account;


@RestController
@RequestMapping("/api")
public class RegisterController {

	@Autowired
	private LearnerService studentService;
	
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/register/role/{role}")
	public ResponseEntity<Object> register(@RequestBody Account account, @PathVariable String role) {
		if(role.equalsIgnoreCase("learner")) {
			Learner student = studentService.checkConflict(account.getEmail());
			if(student != null) return null;
		}else if(role.equalsIgnoreCase("instructor")) {
			Instructor instructor = instructorService.checkConflict(account.getEmail());
			if(instructor != null) return null;
		}else return null;
		
		if(role.equalsIgnoreCase("learner")) {
			Learner newStudent = new Learner();
			newStudent.setName(account.getFullName());
			newStudent.setRole(role);
			newStudent.setPassword(account.getPassword());
			newStudent.setEmail(account.getEmail());
			newStudent.setStatus(true);
			
			studentService.add(newStudent);
			return ResponseEntity.ok(newStudent);
		}else if(role.equalsIgnoreCase("instructor")) {
			Instructor newInstructor = new Instructor();
			newInstructor.setRole(role);
			newInstructor.setRate(0);
			newInstructor.setPassword(account.getPassword());
			newInstructor.setName(account.getFullName());
			newInstructor.setEmail(account.getEmail());
			newInstructor.setStatus(true);
			
			instructorService.add(newInstructor);
			return ResponseEntity.ok(newInstructor);
		}
		else {
			return null;
		}
	}
	
}
