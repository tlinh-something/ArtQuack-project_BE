package com.example.One.Service.IMPL;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.One.DTO.CourseDTO;
import com.example.One.DTO.CourseResponse;
import com.example.One.Entity.Course;
import com.example.One.Repo.CourseRepo;
import com.example.One.Service.CourseService;

import Exception.CourseNotFoundException;

public class CourseServiceImpl implements CourseService {
	
	private  CourseRepo courseRepo;
	
	@Autowired
	public CourseServiceImpl(CourseRepo courseRepo) {
		this.courseRepo = courseRepo;
	}
	



	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		Course course = new Course();
		course.setCourseCode(courseDTO.getCourseCode());
		course.setName(courseDTO.getName());
		course.setInstructorId(courseDTO.getInstructorId());
		course.setCategoryId(courseDTO.getCategoryId());
		course.setLevelId(courseDTO.getLevelId());
        course.setDescription(courseDTO.getDescription());
        course.setUploadDate(courseDTO.getUploadDate());
        course.setPrice(courseDTO.getPrice());
        course.setViewer(courseDTO.getViewer());
        course.setBuyer(courseDTO.getBuyer());
        course.setRate(courseDTO.getRate());
		Course newCourse = courseRepo.save(course);
		
		CourseDTO courseRepo =  new CourseDTO();
		courseRepo.setId(newCourse.getId());
		courseRepo.setCourseCode(newCourse.getCourseCode());
		courseRepo.setInstructorId(newCourse.getInstructorId());
		courseRepo.setCategoryId(newCourse.getCategoryId());
		courseRepo.setLevelId(newCourse.getLevelId());
		courseRepo.setName(newCourse.getName());
		courseRepo.setDescription(newCourse.getDescription());
		courseRepo.setPrice(newCourse.getPrice());
		courseRepo.setBuyer(newCourse.getBuyer());
		courseRepo.setRate(newCourse.getRate());
		return courseRepo;
	
	}
	
	//	public CourseResponse getAllCourse(int pageNo, int pageSize) {
	//	Pageable pageable = PageRequest.of(pageNo, pageSize);
	//	Page<Course> courses = courseRepo.findAll(pageable);
	//	List<Course> listOfCourse = courses.getContent();
	//	List<CourseDTO> content = listOfCourse.stream().map(p -> mapDTO(p)).collect(Collectors.toList());
		
		
		
		//	CourseResponse courseResponse =  new CourseResponse();
		//	courseResponse.setContent(content);
		//	courseResponse.setPageNo(courses.getNumber());
		//	courseResponse.setPageSize(courses.getSize());
		//	courseResponse.setTotalElements(courses.getTotalElements());
		//	courseResponse.setTotalPages(courses.getTotalPages());
		//	courseResponse.setLast(courses.isLast());
	
		//		return courseResponse;
			
		//	}
	
	
	
	
	
	@Override
	public CourseDTO getCourseById(int id) {
		Course course = courseRepo.findById(id).orElseThrow(()
				-> new CourseNotFoundException("Course could not be found"));
		return mapDTO(course);
		
	}
	
		private CourseDTO mapDTO(Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(course.getId());
		courseDTO.setCourseCode(course.getCourseCode());
		courseDTO.setInstructorId(course.getInstructorId());
		courseDTO.setCategoryId(course.getCategoryId());
		courseDTO.setLevelId(course.getLevelId());
		courseDTO.setReviewId(course.getReviewId());
		courseDTO.setDescription(course.getDescription());
		courseDTO.setPrice(course.getPrice());
		courseDTO.setViewer(course.getViewer());
		courseDTO.setBuyer(course.getBuyer());
		courseDTO.setRate(course.getRate());
		return courseDTO;

}




	@Override
	public CourseDTO updateCourse(int id, CourseDTO courseDTO) {
		Course course = courseRepo.findById(id).orElseThrow(()
				-> new CourseNotFoundException("Course could not be updated"));
			course.setName(courseDTO.getName());
		return null;
	}




	@Override
	public void deleteCourse(int id) {
		Course course = courseRepo.findById(id).orElseThrow(()
				-> new CourseNotFoundException("Course could not be "));
				courseRepo.delete(course);
		
	}




	@Override
	public CourseRepo getAllCourse(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
	

		
		
	
	

	
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	


