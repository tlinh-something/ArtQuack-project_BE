package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.LearnerRepository;
import com.swp.ArtQuack.service.TransactionService;
import com.swp.ArtQuack.view.CourseObject;
import com.swp.ArtQuack.view.TransactionObject;
import com.swp.ArtQuack.view.TransactionOfLearner;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swp.ArtQuack.service.LearnerService;
//import com.swp.ArtQuack.view.StudentObject;

@RestController
@RequestMapping("/api")
public class LearnerController {

	@Autowired
	private LearnerService learnerService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private LearnerRepository learnerRepository;
	
	
	@GetMapping("/learners")
	public ResponseEntity<List<Learner>> retrieveAllLearners(){
		return ResponseEntity.ok(learnerService.findAll());
    }	
	
	@GetMapping("/deletedlearners")
	public ResponseEntity<List<Learner>> retrieveAllDeletedLearners(){
		return ResponseEntity.ok(learnerService.findAllDeletedStudents());
    }
	
	@GetMapping("/learners/{keyword}")
	public ResponseEntity<List<Learner>> retrieveLearnersByKeyword(@PathVariable String keyword){
		return ResponseEntity.ok(learnerService.findByNameIgnorecase(keyword));
    }
	@GetMapping("/learner/{learnerID}")
	public ResponseEntity<Learner> retrieveLearner(@PathVariable int learnerID) {
		Learner learner = learnerService.findById(learnerID);
		if(learner != null) {
			return ResponseEntity.status(HttpStatus.OK).body(learner);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/learner")
	public ResponseEntity<Learner> createStudent(@RequestBody Learner learner){
		try {
			if(learnerService.findById(learner.getLearnerID()) != null)
				return ResponseEntity.badRequest().header("message", "Learner with such ID already exists").build();
			
			learner.setStatus(true);
			Learner savedLearner = learnerService.add(learner);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedLearner);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new learner").build();
		}
	}

	@PutMapping("/learner/{learnerID}/updatelearner")
	public ResponseEntity<Learner> updateLearner(@PathVariable int learnerID,@RequestBody Learner learner){
		Learner available = learnerService.findById(learner.getLearnerID());
		if(available == null)
			return  ResponseEntity.badRequest().header("message", "No Learner found for such ID").build();

		learner.setEmail(available.getEmail());
		learner.setPassword(available.getPassword());
		learner.setRole(available.getRole());

		Learner updatedLearner = learnerService.update(learner);
		if(updatedLearner != null)
			return ResponseEntity.ok(updatedLearner);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@DeleteMapping("/learner/{learnerID}")
	public ResponseEntity<Void> deleteLearner(@PathVariable int learnerID){
		try{
			if(learnerService.findById(learnerID) == null)
				return ResponseEntity.notFound().header("message", "No Learner found for such ID").build();

			if(learnerService.delete(learnerID))
				return ResponseEntity.noContent().header("message", "learner deleted successfully").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "learner deletion failed").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "learner deletion failed").build();
		}
	}

	//Transactions
	@GetMapping("/learner/{learnerId}/transactions")
	public ResponseEntity<List<Transaction>> getLearnerTransactions(@PathVariable("learnerId") int learnerId) {
		List<Transaction> transactions = transactionService.getLearnerTransactions(learnerId);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@GetMapping("/{learnerId}/transactions-of-learner")
	public ResponseEntity<List<TransactionOfLearner>> getLearnerTransactions1(@PathVariable int learnerId) {
		try {
			Learner learner = learnerRepository.findById(learnerId)
					.orElseThrow(() -> new NotFoundException("Learner not found"));

			List<TransactionOfLearner> transactionDTOs = mapTransactionsToDTOs(learner.getWallet().getTransactionsTo());
			return ResponseEntity.ok(transactionDTOs);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private List<TransactionOfLearner> mapTransactionsToDTOs(Collection<Transaction> transactions) {
		List<TransactionOfLearner> transactionDTOs = new ArrayList<>();

		for (Transaction transaction : transactions) {
			TransactionOfLearner transactionDTO = new TransactionOfLearner();
			transactionDTO.setTransactionID(transaction.getTransactionID());
			transactionDTO.setDate(transaction.getDate());
			transactionDTO.setMoney(transaction.getMoney());

			Enrollment course = transaction.getEnrollment();
			if (course != null) {
				transactionDTO.setCourseID(course.getCourse().getCourseID());
				transactionDTO.setCourseName(course.getCourse().getName());
				transactionDTO.setTo_instructorID(course.getCourse().getInstructor().getInstructorID());
				transactionDTO.setInstructorName(course.getCourse().getInstructor().getName());
			}

			transactionDTOs.add(transactionDTO);
		}

		return transactionDTOs;
	}
}
