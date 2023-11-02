package com.swp.ArtQuack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.repository.LearnerRepository;
import com.swp.ArtQuack.repository.PostRepository;
import com.swp.ArtQuack.view.AdminCounts;

@RestController
@RequestMapping("/api")
public class AdminController {

	private final InstructorRepository instructorRepoService;
	private final LearnerRepository learnerRepoService;
	private final PostRepository postRepoService;
	private final CourseRepository courseRepoService;
	
	@Autowired
	public AdminController(InstructorRepository instructorRepoService, LearnerRepository learnerRepoService,
			PostRepository postRepoService, CourseRepository courseRepoService) {
		super();
		this.instructorRepoService = instructorRepoService;
		this.learnerRepoService = learnerRepoService;
		this.postRepoService = postRepoService;
		this.courseRepoService = courseRepoService;
	}
	
	@GetMapping("/admin/count")
	public AdminCounts getAdminCount() {
		long instructorCount = (long) instructorRepoService.count();
		long learnerCount =(long) learnerRepoService.count();
		long postCount = (long) postRepoService.count();
		long courseCount = (long) courseRepoService.count();
		return new AdminCounts(instructorCount, learnerCount, postCount, courseCount);
	}
	
}