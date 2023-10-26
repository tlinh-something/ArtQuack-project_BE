package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.service.InstructorService;
import com.swp.ArtQuack.view.CourseObject;
import com.swp.ArtQuack.view.InstructorObject;

@RestController
@RequestMapping("/api")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/instructors")
	public ResponseEntity<List<InstructorObject>> retrieveAllInstructors(){
		List<Instructor> ls = instructorService.findAll();
		List<InstructorObject> list = instructorService.display(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/instructor/{instructorID}")
	public ResponseEntity<InstructorObject> retrieveInstructor(@PathVariable String instructorID) {
		Instructor instructor = instructorService.findById(instructorID);
		if(instructor != null) {
			List<Instructor> ls = new ArrayList<>();
			ls.add(instructor);
			List<InstructorObject> list = instructorService.display(ls);
			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
