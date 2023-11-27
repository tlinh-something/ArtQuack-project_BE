package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.swp.ArtQuack.Enum.CourseStatus;
import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.exception.CourseNotFoundException;
import com.swp.ArtQuack.exception.CourseStatusException;
import com.swp.ArtQuack.exception.InstructorNotFoundException;
import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.view.ChapterObject;
import com.swp.ArtQuack.view.ItemObject;
import com.swp.ArtQuack.view.response.ChapterResponse;
import com.swp.ArtQuack.view.response.CourseResponse;
import com.swp.ArtQuack.view.response.ItemResponse;
import com.swp.ArtQuack.view.response.SubmitListResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private InstructorRepository instructorRepository;

	public List<Course> findAll(boolean status) {
		return courseRepoService.findCoursesByStatus(true);
	}

	public List<Course> findAllCourses(){
		return courseRepoService.findAll();
	}

	public List<Course> findAllOfLearner(){
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

	public List<Course> findCourseReportByInstructorID(int instructorID){
		List<Course> courses = courseRepoService.findCoursesByEnrollmentsListNotEmptyAndInstructorInstructorID(instructorID);
		List<Course> coursesReported = new ArrayList<>();

		for(Course c: courses){
			boolean check = false;

			for(Enrollment e: c.getEnrollmentsList()){
				if(e.getReport() != null && !e.getReport().isEmpty()){
					check = true;
				}
			}

			if (check){
				coursesReported.add(c);
			}
		}
		return coursesReported;
	}

	public List<Enrollment> findCourseReportedByCourseID(int courseID){
		List<Enrollment> enrollments = courseRepoService.findByCourseIdAndReportIsNotNull(courseID);
		return enrollments;
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
			object.setAvatar(x.getAvatar());
			object.setPrice(x.getPrice());
			object.setCourseStatus(x.getCourseStatus());
			object.setReason(x.getReason());
			
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
//	public boolean delete(int courseID) {
//		Course course = findById(courseID);
//		if(course == null) return false;
//		course.setStatus(false);
//		update(course);
//		return !course.isStatus();
//	}

	//List all chapters and items of course
	public List<ChapterObject> getAllChaptersAndItemsInCourse(int courseID) {
		Course course = courseRepoService.findById(courseID)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseID));

		List<ChapterObject> chapterDTOs = new ArrayList<>();
		for (Chapter chapter : course.getChaptersList()) {
			ChapterObject chapterDTO = new ChapterObject(chapter.getChapterID(), chapter.getChapterName(), chapter.isStatus(), chapter.getCourse().getCourseID(), chapter.getCourse().getName());
			List<ItemObject> itemDTOs = chapter.getItemsList().stream()
					.map(item -> new ItemObject(item.getItemID(), item.getItemName(), item.getContent(), item.isStatus(), item.getReport(), item.getTypeofreport(), item.getChapter().getChapterID(), item.getChapter().getChapterName(), item.getChapter().getCourse().getCourseID(), item.getChapter().getCourse().getName()))
					.collect(Collectors.toList());
			chapterDTO.setItems(itemDTOs);
			chapterDTOs.add(chapterDTO);
		}

		return chapterDTOs;
	}

	//Verify course
	public boolean changeCourseState(int courseId, CourseStatus state) {
		Course course = courseRepoService.findById(courseId).orElse(null);
		if (course != null) {
			course.setCourseStatus(state);
			courseRepoService.save(course);
			return true;
		}
		return false;
	}

}
