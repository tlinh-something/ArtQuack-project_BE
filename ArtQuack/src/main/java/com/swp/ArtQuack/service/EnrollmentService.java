package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.Utilities.Constants;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Student;
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
	
	public List<Enrollment> findByStudentID(int studentID){
		List<Enrollment> ls = new ArrayList<Enrollment>();
		ls.addAll(enrollmentRepoService.findByStudentStudentID(studentID));
		return ls;
	}
	
	public List<Enrollment> findByCourseID(int courseID){
		List<Enrollment> ls = new ArrayList<Enrollment>();
		ls.addAll(enrollmentRepoService.findByStudentStudentID(courseID));
		return ls;
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
				y.setDate(x.getDate());
				y.setStatus(x.isStatus());
				
				Course course = x.getCourse();
				y.setCourseID(course.getCourseID());
				y.setCourseName(course.getName());
				
				Student student = x.getStudent();
				y.setStudentID(student.getStudentID());
				y.setStudentName(student.getName());
				list.add(y);
			}
			return list;
		}
}
