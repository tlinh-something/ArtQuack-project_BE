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
import com.swp.ArtQuack.service.CategoryService;
import com.swp.ArtQuack.service.CourseService;
import com.swp.ArtQuack.view.CourseObject;


@RestController
@RequestMapping("/api")
public class CourseController {
	
	@Autowired
	private CourseService courseService;	
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseObject>> retrieveAllCourses(){
		List<Course> ls = courseService.findAll();
		List<CourseObject> list = courseService.displayRender(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/course/{courseID}")
	public ResponseEntity<CourseObject> retrieveGiGService(@PathVariable String courseID) {
		Course course = courseService.findById(courseID);
		if(course != null) {
			List<Course> ls = new ArrayList<>();
			ls.add(course);
			List<CourseObject> list = courseService.displayRender(ls);
			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/Category/{cateID}/courses")
	public ResponseEntity<List<CourseObject>> retrieveAllCoursesOfCategory(@PathVariable String cateID){
		Category category = categoryService.findById(cateID);
		if(category != null) {
			List<Course> ls = courseService.findByCategory(cateID);
			List<CourseObject> list = courseService.displayRender(ls);
			return ResponseEntity.ok(list);
		}
		else return ResponseEntity.notFound().header("message", "No Service Category found for such ID").build();
    }
	
	@GetMapping("/courses/{keyword}")
	public ResponseEntity<List<CourseObject>> retrieveCoursesByKeyword(@PathVariable String keyword){
		List<Course> ls = courseService.findByKeyword(keyword);
		List<CourseObject> list = courseService.displayRender(ls);
		return ResponseEntity.ok(list);
    }
	
//	@PostMapping("/instructor/{instructorID}/category/{cateID}/course")
//	public ResponseEntity<Course> createCourse(@RequestBody CourseObject courseObject, @PathVariable("insrtructorID") String instructorID, @PathVariable("cateID") String cateID) {
//		Course course = courseService.findById(courseObject.getCourseID());
//		if (course != null)
//			return ResponseEntity.notFound().header("message", "Course with such ID already exists").build();
//
//		try {
//			Instructor instructor = courseService.;
//			if(provider == null)
//				return ResponseEntity.notFound().header("message", "Provider with such ID does not exist!").build();
//			
//			GiGService service = giGServiceService.findById(serviceID);
//			if(service == null)
//				return ResponseEntity.notFound().header("message", "Service with such ID does not exist!").build();
//			proService = new ProviderService();
//		
//			proService.setProvider(provider);
//			proService.setService(service);
//			proService.setActive(true);
//			proService.setAvailability(true);
//			proService.setVisible(true);
//			if(providerServiceObject.getDescription() != null) proService.setDescription(providerServiceObject.getDescription());
//			proService.setUnitPrice(providerServiceObject.getUnitPrice());
//			ProviderService savedProviderService = providerServiceService.add(proService);
//			
//			//CREATE IMAGE
//			Image image = new Image();
//			if(providerServiceObject.getLink() != null) image.setLink(providerServiceObject.getLink());
//			image.setProviderService(savedProviderService);
//			imageService.add(image);
//			
//			return ResponseEntity.status(HttpStatus.CREATED).body(savedProviderService);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.header("message", "Failed to add new providerService").build();
//		}
//	}
	
	
//	@PostMapping("/Category/{courseID}/course")
//	public ResponseEntity<Course> createCourse(@PathVariable String courseID, @RequestBody Course course){
//		try {
//			Category category = categoryService.findById(courseID);
//			if(category == null) return ResponseEntity.notFound().header("message", "Category not found. Adding failed").build();
//			
//			if(courseService.findById(course.getCourseID()) != null) 
//				return ResponseEntity.badRequest().header("message", "Course with such ID already exists").build();
//			
//			course.setCategory(category);
//			Course savedCourse = courseService.add(course);
//			if(savedCourse != null)
//				return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
//			else return ResponseEntity.internalServerError().build();
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new course").build();
//		}
//	}
//	
//	@PutMapping("/Category/{courseID}/course")
//	public ResponseEntity<Course> updateCourse(@PathVariable String courseID , @RequestBody Course course){
//		try {
//			Category category = categoryService.findById(courseID);
//			if(category == null) return ResponseEntity.notFound().header("message", "Category not found. Update failed").build();
//			
//			if(courseService.findById(course.getCourseID()) == null) return ResponseEntity.notFound().header("message", "Course with such ID not found. Update failed").build();
//			course.setCategory(category);
//			Course savedCourse = courseService.update(course);
//			if(savedCourse != null)
//				return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
//			else return ResponseEntity.internalServerError().build();
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to update course").build();
//		}
//	}
//	
//	@DeleteMapping("/course/{courseID}")
//	public ResponseEntity<Void> deleteGiGService(@PathVariable String courseID){
//		try{
//			Category category = categoryService.findById(courseID);
//			if(category == null) return ResponseEntity.notFound().header("message", "Service Category not found. Delete failed").build();
//			
//			courseService.delete(courseID);
//			return ResponseEntity.noContent().header("message", "GiGService deleted successfully").build();
//		}
//		catch(Exception e){
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "GiGService deletion failed").build();
//		}
//	}
	
}
