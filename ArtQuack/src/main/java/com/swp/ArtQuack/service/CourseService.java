package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Review;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.view.CourseObject;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepoService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ChapterService chapterService;
	
	public List<Course> findAll(){
		return courseRepoService.findByStatusIsTrue();
	}
	
	public List<Course> findDeleteCourse(){
		return courseRepoService.findByStatusIsFalse();
	}
	
	public Course findById(int Id) {
		return courseRepoService.findByCourseIDAndStatusIsTrue(Id);
	}
	
	public List<Course> findByCategory(int cateID){
		return courseRepoService.findByCategoryCateID(cateID);
	}
	
	public List<Course> findByInstructorID(int instructorID){
		return courseRepoService.findByInstructorInstructorID(instructorID);
	}
	
	public Course findByKeyword(String keyword) {
		return courseRepoService.findByNameContainingIgnoreCaseAndStatusIsTrue(keyword.trim());
	}
	
	//DISPLAY
	public CourseObject displayRender(Course x) {
			CourseObject object = new CourseObject();
			object.setCourseID(x.getCourseID());
			object.setName(x.getName());
			object.setDescription(x.getDescription());
			object.setUpload_date(x.getUpload_date());
			object.setViewer(x.getViewer());
			object.setRate(x.getRate());
			object.setStatus(x.isStatus());
			
			//Instructor
			object.setInstructorID(x.getInstructor().getInstructorID());
			object.setInstructorName(x.getInstructor().getName());
			
			//Category
			object.setCateID(x.getCategory().getCateID());
			object.setCateName(x.getCategory().getCateName());
			
			//Level
			object.setLevelID(x.getLevel().getLevelID());
			object.setLevelName(x.getLevel().getLevelName());
			
//			//Review
//			List<Review> lr = reviewService.findByCourseID(x.getCourseID());
//			object.setRateReview(lr.get(0).getRate());
//			
//			//Chapter
//			List<Chapter> lc = chapterService.findByCourseID(x.getCourseID());
//			object.setChapterName(lc.get(0).getChapterName());
			
		return object;
	}
	
	//ADD
	public Course add(Course course) {
		return courseRepoService.save(course);
	}
	
	//UPDATE
	public Course update(Course newCourse) {
		return add(newCourse);
	}
	
	//DELETE
	public boolean delete(int courseID) {
		Course course = findById(courseID);
		if(course == null) return false;
		course.setStatus(false);
		update(course);
		return !course.isStatus();
	}
}
