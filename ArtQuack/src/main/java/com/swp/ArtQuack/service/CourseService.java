package com.swp.ArtQuack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.repository.CourseRepository;
@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;
	
	public List<Course> findAll(){
		return Course.findByActiveIsTrue();
	}
	
	public Course findById (String courseID) {
		Course course = courseRepo.findByCourseIDAndActiveIsTrue(courseID);
		return course;
	}
	
	public List<Course> findByCategoryID (String cateID) {
		return courseRepo.findByCategoryIDAndActiveIsTrue(cateID.trim());
	}
	
	public List<Course> findLevelID (String levelID) {
		return courseRepo.findByLevelIDAndActiveIsTrue(levelID.trim());
	}
}
