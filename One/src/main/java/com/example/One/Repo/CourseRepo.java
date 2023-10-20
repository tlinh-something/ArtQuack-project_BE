package com.example.One.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.One.Entity.Course;

public interface CourseRepo extends JpaRepository<Course, String> {
	Optional<Course> findById(int id);
	

}
