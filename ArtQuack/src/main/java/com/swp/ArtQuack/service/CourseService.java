package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.view.ChapterObject;
import com.swp.ArtQuack.view.ItemObject;
import com.swp.ArtQuack.view.response.ChapterResponse;
import com.swp.ArtQuack.view.response.CourseResponse;
import com.swp.ArtQuack.view.response.ItemResponse;
import com.swp.ArtQuack.view.response.SubmitListResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.view.CourseObject;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepoService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
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

	public List<Object[]> countCoursesByLevel() {
		return courseRepoService.countCoursesByLevel();
	}
	
	public List<Course> findByInstructorID(int instructorID){
		return courseRepoService.findByInstructorInstructorID(instructorID);
	}
	
	public List<Course> findByKeyword(String keyword) {
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
			object.setAvatar(x.getAvatar());
			object.setPrice(x.getPrice());
			
			//Instructor
			object.setInstructorID(x.getInstructor().getInstructorID());
			object.setInstructorName(x.getInstructor().getName());

			
			//Category
			object.setCateID(x.getCategory().getCateID());
			object.setCateName(x.getCategory().getCateName());
			
			//Level
			object.setLevelID(x.getLevel().getLevelID());
			object.setLevelName(x.getLevel().getLevelName());

			
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

	public List<ChapterObject> getAllChaptersAndItemsInCourse(int courseID) {
		Course course = courseRepoService.findById(courseID)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseID));

		List<ChapterObject> chapterDTOs = new ArrayList<>();
		for (Chapter chapter : course.getChaptersList()) {
			ChapterObject chapterDTO = new ChapterObject(chapter.getChapterID(), chapter.getChapterName(), chapter.isStatus());
			List<ItemObject> itemDTOs = chapter.getItemsList().stream()
					.map(item -> new ItemObject(item.getItemID(), item.getItemName(), item.getContent(), item.isStatus(), item.getChapter().getChapterID(), item.getChapter().getChapterName()))
					.collect(Collectors.toList());
			chapterDTO.setItems(itemDTOs);
			chapterDTOs.add(chapterDTO);
		}

		return chapterDTOs;
	}
}
