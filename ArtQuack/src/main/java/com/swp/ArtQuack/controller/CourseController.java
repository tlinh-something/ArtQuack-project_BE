package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.service.CategoryService;
import com.swp.ArtQuack.service.ChapterService;
import com.swp.ArtQuack.service.CourseService;
import com.swp.ArtQuack.service.InstructorService;
import com.swp.ArtQuack.service.ItemService;
import com.swp.ArtQuack.service.LevelService;
import com.swp.ArtQuack.view.CourseObject;


@RestController
@RequestMapping("/api")
public class CourseController {
	
	@Autowired
	private CourseService courseService;	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private LevelService levelService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseObject>> retrieveAllCourses() {
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findAll();
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/deleted-courses")
	public ResponseEntity<List<CourseObject>> retrieveAllDeletedCourses() {
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findDeleteCourse();
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	
	@GetMapping("/course/{courseID}")
	public ResponseEntity<CourseObject> retrieveCourse(@PathVariable int courseID) {
		Course course = courseService.findById(courseID);
		if (course != null) {
			return ResponseEntity.status(HttpStatus.OK).body(courseService.displayRender(course));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/category/{cateID}/courses")
	public ResponseEntity<List<CourseObject>> findByCateID(@PathVariable("cateID") int cateID){
		Category category = categoryService.findById(cateID);
		if(category == null)
			return ResponseEntity.notFound().header("message", "No Category found for such ID").build();
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findByCategory(cateID);
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
		
	@GetMapping("/courses/{keyword}")
	public ResponseEntity<List<CourseObject>> retrieveCourseByKeyword(@PathVariable String keyword) {
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findByKeyword(keyword);
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/instructor/{instructorID}/coursesOfInstructor")
	public ResponseEntity<List<CourseObject>> findByInstructorID(@PathVariable int instructorID) {
		Instructor instructor = instructorService.findById(instructorID);
		if (instructor != null) {
			List<CourseObject> ls = new ArrayList<CourseObject>();
			List<Course> courseList = courseService.findByInstructorID(instructorID);
			for(Course x: courseList) {
				ls.add(courseService.displayRender(x));
			}
			return ResponseEntity.ok(ls);
		} else
			return ResponseEntity.notFound().header("message", "No Provider found for such ID").build();
	}
	
	@PostMapping("/instructor/{instructorID}/category/{cateID}/level/{levelID}/course")
	public ResponseEntity<Course> createCourse(@PathVariable int instructorID,@PathVariable int cateID, @PathVariable int levelID, @RequestBody Course course){
		try {
			Instructor instructor = instructorService.findById(instructorID);
			if(instructor == null) return ResponseEntity.notFound().header("message", "Instructor not found. Adding failed").build();
			
			Category category = categoryService.findById(cateID);
			if(category == null) return ResponseEntity.notFound().header("message", "Category not found. Adding failed").build();
			
			Level level = levelService.findById(levelID);
			if(level == null) return ResponseEntity.notFound().header("message", "Level not found. Adding failed").build();
			
			if(courseService.findById(course.getCourseID()) != null) 
				return ResponseEntity.badRequest().header("message", "Course with such ID already exists").build();
			
			course.setInstructor(instructor);
			course.setCategory(category);
			course.setLevel(level);
			course.setStatus(true);
			Course savedCourse = courseService.add(course);
			if(savedCourse != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new course").build();
		}
	}
	
	@PutMapping("/course/{courseID}/updatecourse")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseID") int courseID , @RequestBody Course course){
		Course available = courseService.findById(course.getCourseID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Course found for such ID").build();
		
		course.setStatus(true);
		Course updatedCourse = courseService.update(course);
		if(updatedCourse != null)
			return ResponseEntity.ok(updatedCourse);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	
	@DeleteMapping("/deletecourse/{courseID}")
	public ResponseEntity<Void> deleteCourse(@PathVariable int courseID){
		try{
			Course course = courseService.findById(courseID);
			if(course == null) return ResponseEntity.notFound().header("message", "Course not found. Delete failed").build();
			
			courseService.delete(courseID);
			return ResponseEntity.noContent().header("message", "Course deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Course deletion failed").build();
		}
	}
	
}
