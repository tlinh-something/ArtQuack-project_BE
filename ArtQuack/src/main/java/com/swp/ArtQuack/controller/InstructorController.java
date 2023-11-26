package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.service.TransactionService;
import com.swp.ArtQuack.view.TransactionObject;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.service.InstructorService;
import com.swp.ArtQuack.view.InstructorObject;

@RestController
@RequestMapping("/api")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private InstructorRepository instructorRepository;
	
	@GetMapping("/instructors")
	public ResponseEntity<List<InstructorObject>> retrieveAllInstructors(){
		List<Instructor> ls = instructorService.findAll();
		List<InstructorObject> list = instructorService.display(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/instructor-by-true")
	public ResponseEntity<List<InstructorObject>> retrieveInstructorsByTrue(){
		List<Instructor> ls = instructorService.findInstructorsByTrue();
		List<InstructorObject> list = instructorService.display(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/instructor/{instructorID}")
	public ResponseEntity<InstructorObject> retrieveInstructor(@PathVariable int instructorID) {
		Instructor instructor = instructorService.findById(instructorID);
		if(instructor != null) {
			List<Instructor> ls = new ArrayList<>();
			ls.add(instructor);
			List<InstructorObject> list = instructorService.display(ls);
			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/createinstructor")
	public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor){
		Instructor savedInstructor = instructorService.add(instructor);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructor);
	}
	
	@PutMapping("/instructor/{instructorID}/updateinstructor")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable String instructorID, @RequestBody Instructor instructor){
		Instructor available = instructorService.findById(instructor.getInstructorID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Instructor found for such ID").build();
		
		instructor.setEmail(available.getEmail());
		instructor.setPassword(available.getPassword());
		instructor.setRole(available.getRole());
		
		Instructor updatedInstructor = instructorService.update(instructor);
		if(updatedInstructor != null)
			return ResponseEntity.ok(updatedInstructor);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DeleteMapping("/instructor/{instructorID}")
	public ResponseEntity<Void> deleteInstructor(@PathVariable int instructorID){
		try{
			if(instructorService.findById(instructorID) == null)
			return ResponseEntity.notFound().header("message", "No Instructor found for such ID").build();
		
			if(instructorService.delete(instructorID))
				return ResponseEntity.noContent().header("message", "instructor deleted successfully").build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "instructor deletion failed").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "instructor deletion failed").build();
		}
	}

	//Transaction
	@GetMapping("/instructor/{instructorId}/transactions")
	public ResponseEntity<List<Transaction>> getInstructorTransactions(@PathVariable("instructorId") int instructorId) {
		List<Transaction> transactions = transactionService.getInstructorTransactions(instructorId);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}


	@GetMapping("/{instructorId}/transactions-of-instructor")
	public ResponseEntity<List<TransactionObject>> getInstructorTransactions1(@PathVariable int instructorId) {
		try {
			Instructor instructor = instructorRepository.findById(instructorId)
					.orElseThrow(() -> new NotFoundException("Instructor not found"));

			List<TransactionObject> transactionDTOs = mapTransactionsToDTOs(instructor.getWallet().getTransactionsTo());
			return ResponseEntity.ok(transactionDTOs);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	private List<TransactionObject> mapTransactionsToDTOs(Collection<Transaction> transactions) {
		List<TransactionObject> transactionDTOs = new ArrayList<>();

		for (Transaction transaction : transactions) {
			TransactionObject transactionDTO = new TransactionObject();
			transactionDTO.setTransactionID(transaction.getTransactionID());
			transactionDTO.setDate(transaction.getDate());
			transactionDTO.setMoney(transaction.getMoney());

			Enrollment course = transaction.getEnrollment();
			if (course != null) {
				transactionDTO.setCourseID(course.getCourse().getCourseID());
				transactionDTO.setCourseName(course.getCourse().getName());
				transactionDTO.setFrom_learnerID(course.getLearner().getLearnerID());
				transactionDTO.setLearnerName(course.getLearner().getName());
			}

			transactionDTOs.add(transactionDTO);
		}

		return transactionDTOs;
	}
}
