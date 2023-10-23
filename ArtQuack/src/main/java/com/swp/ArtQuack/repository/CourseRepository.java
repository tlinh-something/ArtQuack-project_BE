package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import com.swp.ArtQuack.entity.Course;
import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course,String>,JpaSpecificationExecutor<Course>{
	public Course findByCourseIDAndActiveIsTrue(String courseID);
	
	public List<Course> findByActiveIsTrue();
	
	public List<Course> findByCategoryIDAndActiveIsTrue(String cateID);
	
	public List<Course> findByLevelIDAndActiveIsTrue(String levelID);
}
