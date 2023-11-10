package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course>{

	public Course findByCourseIDAndStatusIsTrue(int courseID);
	
	public List<Course> findByStatusIsTrue();
	
	public List<Course> findByStatusIsFalse();
	
	public List<Course> findByCategoryCateID(int cateID);
	
	public List<Course> findByNameContainingIgnoreCaseAndStatusIsTrue(String name);
	
	public List<Course> findByInstructorInstructorID(int instructorID);

	public List<Course>  findAllOfCourseByCourseID(int courseID);

	@Query("SELECT c.level.levelName, COUNT(c) FROM Course c GROUP BY c.level.levelName")
	List<Object[]> countCoursesByLevel();
	
}
