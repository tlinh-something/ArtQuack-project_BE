package com.swp.ArtQuack.repository;

import java.util.List;

import com.swp.ArtQuack.Enum.CourseStatus;
import com.swp.ArtQuack.entity.Enrollment;
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

	public List<Course> findCoursesByStatus(boolean status);

	public List<Course> findByStatusIsTrue();
	public List<Course> findByStatusIsFalse();
	
	public List<Course> findByCategoryCateID(int cateID);
	
	public List<Course> findByNameContainingIgnoreCaseAndStatusIsTrue(String name);
	
	public List<Course> findByInstructorInstructorID(int instructorID);

	public List<Course> findCoursesByEnrollmentsListNotEmptyAndInstructorInstructorID(int instructorID);

	@Query("SELECT e FROM Enrollment e WHERE e.course.courseID = ?1 AND e.report IS NOT NULL")
	List<Enrollment> findByCourseIdAndReportIsNotNull(int courseID);

	@Query("SELECT c.level.levelName, COUNT(c) FROM Course c GROUP BY c.level.levelName")
	List<Object[]> countCoursesByLevel();
	
}
