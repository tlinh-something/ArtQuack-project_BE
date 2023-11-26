package com.swp.ArtQuack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.repository.EnrollmentRepository;
import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.repository.LearnerRepository;
import com.swp.ArtQuack.view.AdminCounts;

@RestController
@RequestMapping("/api")
public class AdminController {

	 InstructorRepository instructorRepoService;
	 LearnerRepository learnerRepoService;
	 CourseRepository courseRepoService;
	 EnrollmentRepository enrollmentRepoService;

	@Autowired
	public AdminController(InstructorRepository instructorRepoService, LearnerRepository learnerRepoService,
			 CourseRepository courseRepoService,
			EnrollmentRepository enrollmentRepoService) {
		super();
		this.instructorRepoService = instructorRepoService;
		this.learnerRepoService = learnerRepoService;
		this.courseRepoService = courseRepoService;
		this.enrollmentRepoService = enrollmentRepoService;
	}
	
	@GetMapping("/admin/count")
	public AdminCounts getAdminCount() {
		long instructorCount = (long) instructorRepoService.count();
		long learnerCount =(long) learnerRepoService.count();
		long courseCount = (long) courseRepoService.count();
		long enrollmentCount = (long) enrollmentRepoService.count();
		return new AdminCounts(instructorCount, learnerCount, courseCount, enrollmentCount);
	}
	
}