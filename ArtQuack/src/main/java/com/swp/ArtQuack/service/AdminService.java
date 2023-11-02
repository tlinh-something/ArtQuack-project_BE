//package com.swp.ArtQuack.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.swp.ArtQuack.repository.CourseRepository;
//import com.swp.ArtQuack.repository.InstructorRepository;
//import com.swp.ArtQuack.repository.LearnerRepository;
//import com.swp.ArtQuack.repository.PostRepository;
//
//@Service
//public class AdminService {
//
//	@Autowired
//	private InstructorRepository instructorRepoService;
//	
//	@Autowired
//	private LearnerRepository learnerRepoService;
//	
//	@Autowired
//	private PostRepository postRepoService;
//	
//	@Autowired
//	private CourseRepository courseRepoService;
//	
//	public long getCount() {
//		return instructorRepoService.count(),
//					learnerRepoService.count(),
//					courseRepoService.count(),
//					postRepoService.count();
//	}
//}
