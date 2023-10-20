package com.example.One.Service;



import org.springframework.stereotype.Service;

import com.example.One.DTO.CourseDTO;
import com.example.One.Repo.CourseRepo;




@Service
public interface CourseService {

	
	CourseDTO createCourse(CourseDTO courseDTO);

	CourseDTO updateCourse(int id, CourseDTO courseDTO);

	void deleteCourse(int id);

	CourseDTO getCourseById(int id);

	CourseRepo getAllCourse(int pageNo, int pageSize);
	
	
	
	

}
