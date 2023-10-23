package com.swp.ArtQuack.controller;

import java.util.List;

public class CourseController {
	@Autowired 
	private CourseService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> retrieveAllCourses(){
		return ResponseEntity.ok(courseService.findAll());
	}
}
