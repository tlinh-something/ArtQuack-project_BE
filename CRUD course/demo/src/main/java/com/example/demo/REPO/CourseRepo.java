package com.example.demo.REPO;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, String> {
}
