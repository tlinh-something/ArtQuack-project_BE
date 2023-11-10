package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Enrollment;
import java.util.Date;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Course;



@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>, JpaSpecificationExecutor<Enrollment>{
	
	
	public Enrollment findByEnrollmentIDAndStatusIsTrue(int enrollmentID);
	
	public List<Enrollment>  findByLearnerLearnerID(int learnerID);
	
	public List<Enrollment> findByCourseCourseID(int courseID);
	
	public List<Enrollment> findByDate(Date date);
	
	public List<Enrollment>  findByCourseCourseIDAndLearnerLearnerID(int courseID, int learnerID);
	
	public boolean existsByLearnerLearnerIDAndCourseCourseID(int learnerID, int courseID);

}
