package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.*;
import com.swp.ArtQuack.service.TransactionService;
import com.swp.ArtQuack.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

	 InstructorRepository instructorRepoService;
	 LearnerRepository learnerRepoService;
	 CourseRepository courseRepoService;
	 EnrollmentRepository enrollmentRepoService;

	 @Autowired
	 private AdminRepository adminRepository;

	 @Autowired
	 private TransactionRepository transactionRepository;

	 @Autowired
	 private TransactionService transactionService;

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

//	@GetMapping("/all-transactions")
//	public ResponseEntity<List<WithdrawalRequest>> retrieveAllTransactions(){
//		List<WithdrawalRequest> ls = new ArrayList<WithdrawalRequest>();
//		List<Transaction> trasactionList = transactionService.retrieveAllTransactions();
//		for(Transaction x: trasactionList) {
//			ls.add(transactionService.displayRender(x));
//		}
//		return ResponseEntity.ok(ls);
//	}
	
}