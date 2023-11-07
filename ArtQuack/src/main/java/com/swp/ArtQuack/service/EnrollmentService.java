package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.repository.EnrollmentRepository;
import com.swp.ArtQuack.view.EnrollmentObject;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepoService;
	
	public List<Enrollment> findAll(){
		return enrollmentRepoService.findAll();
	}
	
	public Enrollment findById(int enrollmentID) {
		Optional<Enrollment> enroll = enrollmentRepoService.findById(enrollmentID);
		if(enroll.isPresent()) return enroll.get();
		else return null;
	}
	
	public List<Enrollment> findByDate(Date date){
		return enrollmentRepoService.findByDate(date);
	}
	
	public List<Enrollment> findByLearnerID(int learnerID){
		List<Enrollment> ls = new ArrayList<Enrollment>();
		ls.addAll(enrollmentRepoService.findByLearnerLearnerID(learnerID));
		return ls;
	}
	
	public List<Enrollment> findByCourseID(int courseID){
		List<Enrollment> ls = new ArrayList<Enrollment>();
		ls.addAll(enrollmentRepoService.findByCourseCourseID(courseID));
		return ls;
	}
	
	public boolean hasEnrolled(int learnerID, int courseID) {
        return enrollmentRepoService.existsByLearnerLearnerIDAndCourseCourseID(learnerID, courseID);
    }
	
	//ADD
	public Enrollment add(Enrollment enrollment) {
		return enrollmentRepoService.save(enrollment);
	}
	
	//UPDATE
	public Enrollment update(Enrollment newEnroll) {
		return enrollmentRepoService.save(newEnroll);
	}
	
	//DELETE
		public boolean delete(int id) {
			enrollmentRepoService.deleteById(id);
			return enrollmentRepoService.findById(id).isEmpty();
		}
		
	//DISPLAY
		public List<EnrollmentObject> display(List<Enrollment> ls){
			List<EnrollmentObject> list = new ArrayList<EnrollmentObject>();
			for(Enrollment x: ls) {
				EnrollmentObject y = new EnrollmentObject();
				y.setEnrollmentID(x.getEnrollmentID());
				y.setRate(x.getRate());
				y.setComment(x.getComment());
				y.setDate(x.getDate());
				y.setStatus(x.isStatus());

				y.setInstructorID(x.getCourse().getInstructor().getInstructorID());
				y.setInstructorName(x.getCourse().getInstructor().getName());
				
				Course course = x.getCourse();
				y.setCourseID(course.getCourseID());
				y.setName(course.getName());
				y.setDescription(x.getCourse().getDescription());
				y.setRateCourse(x.getCourse().getRate());
				y.setUpload_date(x.getCourse().getUpload_date());
				y.setViewer(x.getCourse().getViewer());
				y.setAvatar(x.getCourse().getAvatar());
				y.setPrice(x.getCourse().getPrice());
				y.setCateID(x.getCourse().getCategory().getCateID());
				y.setCateName(x.getCourse().getCategory().getCateName());
				y.setLevelID(x.getCourse().getLevel().getLevelID());
				y.setLevelName(x.getCourse().getLevel().getLevelName());
				
				Learner learner = x.getLearner();
				y.setLearnerID(learner.getLearnerID());
				y.setLearnerName(learner.getName());
				list.add(y);
			}
			return list;
		}
}
