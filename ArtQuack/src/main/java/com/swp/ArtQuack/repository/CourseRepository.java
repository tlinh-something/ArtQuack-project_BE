package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;


@Repository
public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course>{

	public Course findByCourseIDAndStatusIsTrue(String courseID);
	
	public List<Course> findByStatusIsTrue();
	
	public List<Course> findByStatusIsFalse();
	
	public List<Course> findByCategoryCateID(String cateID);
	
	public List<Course> findByNameContainingIgnoreCaseAndStatusIsTrue(String name);
	
	public List<Course> findByInstructorIDAndStatusIsTrue(String instructorID);
	
}
