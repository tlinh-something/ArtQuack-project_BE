package com.swp.ArtQuack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.service.LearnerService;
//import com.swp.ArtQuack.view.StudentObject;

@RestController
@RequestMapping("/api")
public class LearnerController {

	@Autowired
	private LearnerService learnerService;
	
	
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
}
