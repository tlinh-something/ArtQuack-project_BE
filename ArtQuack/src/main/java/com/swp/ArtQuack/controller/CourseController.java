package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

import com.swp.ArtQuack.Enum.CourseStatus;
import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.exception.CourseNotFoundException;
import com.swp.ArtQuack.exception.CourseStatusException;
import com.swp.ArtQuack.exception.InstructorNotFoundException;
import com.swp.ArtQuack.repository.ChapterRepository;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.repository.EnrollmentRepository;
import com.swp.ArtQuack.service.*;
import com.swp.ArtQuack.view.*;
import com.swp.ArtQuack.view.response.CourseResponse;
import org.apache.coyote.http11.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private EmailService emailService;


	@GetMapping("/allCourses")
	public ResponseEntity<List<CourseObject>> retrieveAllCourses(){
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findAllCourses();
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}

	@GetMapping("/courses")
	public ResponseEntity<List<CourseObject>> retrieveCourses() {
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findAll(true);
		for(Course x: courseList) {
			ls.add(courseService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}

	@GetMapping("/count-by-level")
	public List<Object[]> countCoursesByLevel() {
		return courseService.countCoursesByLevel();
	}
	
	@GetMapping("/courses-learner/{learnerID}")
	public ResponseEntity<List<CourseObject>> retrieveAllCourses(@PathVariable int learnerID) {
		List<CourseObject> ls = new ArrayList<CourseObject>();
		List<Course> courseList = courseService.findAllOfLearner();
		for(Course x: courseList) {
			boolean check = enrollmentRepository.existsByLearnerLearnerIDAndCourseCourseID(learnerID, x.getCourseID());
			if(!check){
				ls.add(courseService.displayRender(x));
			}
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
	
	@GetMapping("/course/{courseID}/{learnerID}")
	public ResponseEntity<CourseObject> retrieveCourse(@PathVariable int courseID, @PathVariable int learnerID) {
		Course course = courseService.findById(courseID);
		if (course != null) {
			CourseObject courseObject = courseService.displayRender(course);
			courseObject.setEnrolled(enrollmentRepository.existsByLearnerLearnerIDAndCourseCourseID(learnerID, courseID));
			return ResponseEntity.status(HttpStatus.OK).body(courseObject);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/course/{courseID}")
	public ResponseEntity<CourseObject> retrieveCourseByID(@PathVariable int courseID) {
		Course course = courseService.findById(courseID);
		if (course != null) {
			return ResponseEntity.status(HttpStatus.OK).body(courseService.displayRender(course));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/all-of-course/{courseID}")
	public ResponseEntity<List<ChapterObject>> getAllChaptersAndItemsInCourse(@PathVariable int courseID) {
		List<ChapterObject> chapters = courseService.getAllChaptersAndItemsInCourse(courseID);
		return ResponseEntity.ok(chapters);
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

	@GetMapping("/instructor/{instructorID}/courses-chapters-items")
	public ResponseEntity<List<CourseDTO>> getInstructorCourses(@PathVariable int instructorID) {
		// Retrieve the instructor's courses with status set to true
		List<Course> courses = courseRepository.findByInstructorInstructorID(instructorID);

		// Create a list to hold the course DTOs
		List<CourseDTO> courseDtos = new ArrayList<>();

		// Iterate through each course and populate the DTOs
		for (Course course : courses) {
			CourseDTO courseDto = new CourseDTO();
			courseDto.setCourseID(course.getCourseID());
			courseDto.setName(course.getName());
			courseDto.setDescription(course.getDescription());
			courseDto.setUploadDate(course.getUpload_date());
			courseDto.setViewer(course.getViewer());
			courseDto.setRate(course.getRate());
			courseDto.setStatus(course.isStatus());
			courseDto.setCourseStatus(course.getCourseStatus());
			courseDto.setAvatar(course.getAvatar());
			courseDto.setPrice(course.getPrice());

			// Retrieve the chapters with status set to true for the current course
			List<ChapterDTO> chapterDtos = new ArrayList<>();
			for (Chapter chapter : course.getChaptersList()) {
				if (chapter.isStatus()) {
					ChapterDTO chapterDto = new ChapterDTO();
					chapterDto.setChapterID(chapter.getChapterID());
					chapterDto.setChapterName(chapter.getChapterName());
					chapterDto.setStatus(chapter.isStatus());

					// Retrieve the items with status set to true for the current chapter
					List<ItemDTO> itemDtos = new ArrayList<>();
					for (Item item : chapter.getItemsList()) {
						if (item.isStatus()) {
							ItemDTO itemDto = new ItemDTO();
							itemDto.setItemID(item.getItemID());
							itemDto.setItemName(item.getItemName());
							itemDto.setContent(item.getContent());
							itemDto.setStatus(item.isStatus());

							itemDtos.add(itemDto);
						}
					}

					chapterDto.setItemsList(itemDtos);
					chapterDtos.add(chapterDto);
				}
			}

			courseDto.setChaptersList(chapterDtos);
			courseDtos.add(courseDto);
		}

		return ResponseEntity.ok(courseDtos);
	}

	//List all chapters and items of course
	@GetMapping("/course/{courseID}/chapters-items")
	public ResponseEntity<List<ChapterDTO>> getCourseOfInstructorChaptersItems(@PathVariable int courseID) {
		// Retrieve the instructor's courses with status set to true
		List<Chapter> chapters = chapterRepository.findByCourseCourseID(courseID);

		// Create a list to hold the course DTOs
		List<ChapterDTO> chapterDtos = new ArrayList<>();

		// Iterate through each course and populate the DTOs
		for (Chapter chapter : chapters) {
			if (chapter.isStatus()) {
				ChapterDTO chapterDto = new ChapterDTO();
				chapterDto.setChapterID(chapter.getChapterID());
				chapterDto.setChapterName(chapter.getChapterName());
				chapterDto.setSeevideo(chapter.isSeevideo());
				chapterDto.setStatus(chapter.isStatus());
				// Retrieve the chapters with status set to true for the current course
				List<ItemDTO> itemDtos = new ArrayList<>();
				for (Item item : chapter.getItemsList()) {
					if (item.isStatus()) {
						ItemDTO itemDto = new ItemDTO();
						itemDto.setItemID(item.getItemID());
						itemDto.setItemName(item.getItemName());
						itemDto.setContent(item.getContent());
						itemDto.setStatus(item.isStatus());

						itemDtos.add(itemDto);
					}

				}
				chapterDto.setItemsList(itemDtos);
				chapterDtos.add(chapterDto);
			}
		}

		return ResponseEntity.ok(chapterDtos);
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
//			course.setCourseStatus(CourseStatus.UPDATING);
			Course savedCourse = courseService.add(course);
			if(savedCourse != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new course").build();
		}
	}
	
	@PutMapping("/course/{courseID}/updatecourse")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseID") int courseID , @RequestBody Course course){
		Course available = courseService.findById(course.getCourseID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Course found for such ID").build();

		course.setStatus(available.isStatus());
		course.setCourseStatus(available.getCourseStatus());
		Course updatedCourse = courseService.update(course);
		if(updatedCourse != null)
			return ResponseEntity.ok(updatedCourse);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	//UPDATE FOR COURSESTATUS
	@PutMapping("/{courseId}/verify")
	public ResponseEntity<Course> verifyCourse(@PathVariable int courseId) {
		Course course = courseService.findById(courseId);

		// Check if course exists
		if (course == null) {
			return ResponseEntity.notFound().build();
		}

		// Update course status to VERIFY
		course.setCourseStatus(CourseStatus.VERIFY);

		Course updatedCourse = courseService.update(course);
		return ResponseEntity.ok(updatedCourse);
	}
	
	
	@DeleteMapping("/deletecourse/{courseID}")
	public ResponseEntity<String> deleteCourse(@PathVariable("courseID") int courseID) {
		// Find the course by ID
		Course course = courseService.findById(courseID);

		if (course == null) {
			return ResponseEntity.notFound().build();
		}

		// Set the course status to false
		courseService.add(course);

		// Deactivate enrollments for the course
		List<Enrollment> enrollments = enrollmentService.findByCourseID(courseID);
		for (Enrollment enrollment : enrollments) {
			enrollment.setStatus(false);
			enrollmentService.add(enrollment);
		}

		return ResponseEntity.ok("Course deleted successfully.");
	}

	@GetMapping("/get-course-report/{instructorID}")
	public ResponseEntity<List<Course>> retrieveCourseReported(@PathVariable("instructorID")int instructorID){
		List<Course> courses = courseService.findCourseReportByInstructorID(instructorID);
		return  ResponseEntity.ok(courses);
	}

	@GetMapping("/get-reports-of-course/{courseID}")
	public ResponseEntity<List<EnrollmentObject>> retrieveReportsOfCourse(@PathVariable("courseID")int courseID){
		List<Enrollment> enrollments = courseService.findCourseReportedByCourseID(courseID);
		List<EnrollmentObject> enrollmentObjects = enrollmentService.display(enrollments);
		return ResponseEntity.ok(enrollmentObjects);
	}

	@PatchMapping ("/{courseId}/change-course-status")
	public ResponseEntity<String> changeCourseState(@PathVariable int courseId, @RequestParam CourseStatus state) {
		boolean success = courseService.changeCourseState(courseId, state);
		if (success) {
			return ResponseEntity.ok("Course state changed successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
		}
	}


	//ADMIN APPROVE OR REJECT
	@PutMapping("/{courseId}/approve")
	public ResponseEntity<Course> approveCourse(@PathVariable int courseId) {
		Course course = courseService.findById(courseId);

		// Check if course exists
		if (course == null) {
			return ResponseEntity.notFound().build();
		}

		// Update course status to ACTIVE
		course.setCourseStatus(CourseStatus.ACTIVE);

		Course approvedCourse = courseService.update(course);
		return ResponseEntity.ok(approvedCourse);
	}

	@PutMapping("/{courseId}/reject")
	public ResponseEntity<Course> updateCourseToReject(@PathVariable("courseId") int courseID , @RequestBody Course course){
		Course available = courseService.findById(courseID);
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Course found for such ID").build();

		course.setName(available.getName());
		course.setAvatar(available.getAvatar());
		course.setRate(available.getRate());
		course.setPrice(available.getPrice());
		course.setViewer(available.getViewer());
		course.setUpload_date(available.getUpload_date());
		course.setDescription(available.getDescription());
		course.setStatus(available.isStatus());
		course.setCourseStatus(CourseStatus.REJECT);
		Course updatedCourse = courseService.update(course);
		EmailDetail emailDetail = new EmailDetail();
		emailDetail.setRecipient(updatedCourse.getInstructor().getEmail());
		emailDetail.setToName(updatedCourse.getInstructor().getName());
		emailDetail.setCourseName(updatedCourse.getName());
		emailDetail.setSubject("Response for Course Rejected");
		emailDetail.setMsgBody("aaa");
		emailService.sendMailTemplate(emailDetail, "Reject-for-course");
		if(updatedCourse != null)
			return ResponseEntity.ok(updatedCourse);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	//CHANGE COURSE IS DELETED TO VERIFY AGAINST
	@PutMapping("/{courseId}/activate")
	public ResponseEntity<String> activateCourse(@PathVariable("courseId") int courseId) {
		Course course = courseService.findById(courseId);

		if (course == null) {
			return ResponseEntity.notFound().build();
		}

		course.setCourseStatus(CourseStatus.VERIFY);

		Course updatedCourse = courseService.add(course);

		if (updatedCourse != null) {
			return ResponseEntity.ok("Course status changed to true and courseStatus set to VERIFY.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to update the course. Please try again.");
		}
	}
}
