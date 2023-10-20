package com.example.One.Controller;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.One.DTO.CourseDTO;
import com.example.One.Entity.Course;
import com.example.One.Service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	private CourseService courseService;
	
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Autowired
	private ObjectMapper objectMapper;
    private Course course;
    private CourseDTO courseDto;
    
    
    @GetMapping("course/{id}")
    public ResponseEntity<CourseDTO> courseDetail(@PathVariable int id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    
    @PostMapping("course/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDto){
        return new ResponseEntity<>(courseService.createCourse(courseDto),HttpStatus.CREATED);
    }
    @PutMapping("course/{id}/update")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") int courseId){
    	CourseDTO response = courseService.updateCourse(courseId, courseDTO);
        return null;
    }

    @DeleteMapping("course/{id}/delete")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") int courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("course delete", HttpStatus.OK);
    }
	
    
    



}
