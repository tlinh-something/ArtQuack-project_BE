package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>{

}
