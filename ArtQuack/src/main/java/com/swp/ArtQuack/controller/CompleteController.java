package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

import com.swp.ArtQuack.service.EmailService;
import com.swp.ArtQuack.view.EmailDetail;
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

import com.swp.ArtQuack.entity.Complete;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.service.CompleteService;
import com.swp.ArtQuack.service.ItemService;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.view.CompleteObject;

@RestController
@RequestMapping("/api")
public class CompleteController {

	@Autowired
	private CompleteService completeService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private LearnerService learnerService;

	@Autowired
	EmailService emailService;
	
	@GetMapping("/completes")
	public ResponseEntity<List<CompleteObject>> retrieveAllCompletes() {
		List<CompleteObject> ls = new ArrayList<CompleteObject>();
		List<Complete> completeList = completeService.findAll();
		for(Complete x: completeList) {
			ls.add(completeService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/complete/{completeID}")
	public ResponseEntity<CompleteObject> retrieveComplete(@PathVariable int completeID) {
		Complete complete = completeService.findById(completeID);
		if (complete != null) {
			return ResponseEntity.status(HttpStatus.OK).body(completeService.displayRender(complete));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/Item/{itemID}/completes")
	public ResponseEntity<List<CompleteObject>> findByItemID(@PathVariable("itemID") int itemID){
		Item item = itemService.findById(itemID);
		if(item == null)
			return ResponseEntity.notFound().header("message", "No Item found for such ID").build();
		List<CompleteObject> ls = new ArrayList<CompleteObject>();
		List<Complete> completeList = completeService.findByItemId(itemID);
		for(Complete x: completeList) {
			ls.add(completeService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/Learner/{learnerID}/completes")
	public ResponseEntity<List<CompleteObject>> findByLearnerID(@PathVariable("learnerID") int learnerID){
		Learner learner = learnerService.findById(learnerID);
		if(learner == null)
			return ResponseEntity.notFound().header("message", "No Learner found for such ID").build();
		List<CompleteObject> ls = new ArrayList<CompleteObject>();
		List<Complete> completeList = completeService.findByLearnerId(learnerID);
		for(Complete x: completeList) {
			ls.add(completeService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}

	@GetMapping("/Learner/{learnerID}/Item/{itemID}/complete")
	public ResponseEntity<List<CompleteObject>> findByLearnerIDAndItemID(@PathVariable("learnerID") int learnerID, @PathVariable("itemID") int itemID){
		Learner learner = learnerService.findById(learnerID);
		if(learner == null)
			return ResponseEntity.notFound().header("message", "No Learner found for such ID").build();

		Item item = itemService.findById(itemID);
		if(item == null)
			return ResponseEntity.notFound().header("message", "No Item found for such ID").build();

		List<CompleteObject> ls = new ArrayList<>();
		List<Complete> completeList = completeService.findByLearnerIDAndItemID(learnerID, itemID);
		for(Complete x: completeList) {
			ls.add(completeService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@PostMapping("/Item/{itemID}/Learner/{learnerID}/complete")
	public ResponseEntity<Complete> createComplete(@PathVariable("itemID") int itemID,@PathVariable("learnerID") int learnerID, @RequestBody Complete complete){
		try {
			Item item = itemService.findById(itemID);
			if(item == null) return ResponseEntity.notFound().header("message", "Item not found. Adding failed").build();
			
			Learner learner = learnerService.findById(learnerID);
			if(learner == null) return ResponseEntity.notFound().header("message", "Learner not found. Adding failed").build();


			if(completeService.findById(complete.getCompleteID()) != null)
				return ResponseEntity.badRequest().header("message", "Complete with such ID already exists").build();

			complete.setLearner(learner);
			complete.setItem(item);
			complete.setStatus(true);
			Complete savedComplete = completeService.add(complete);
			EmailDetail emailDetail = new EmailDetail();
			emailDetail.setRecipient(complete.getItem().getChapter().getCourse().getInstructor().getEmail());
			emailDetail.setToName(complete.getItem().getChapter().getCourse().getInstructor().getName());
			emailDetail.setFromName(complete.getLearner().getName());
			emailDetail.setSubject("Request for Submission Grading");
			emailDetail.setMsgBody("aaa");
			emailService.sendMailTemplate(emailDetail, "emailtemplate");

			if(savedComplete != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedComplete);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new complete").build();
		}
	}
	
	@PutMapping("/complete/{completeID}/updatecomplete")
	public ResponseEntity<Complete> updateComplete(@PathVariable("completeID") int completeID , @RequestBody Complete complete){
		Complete available = completeService.findById(complete.getCompleteID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Complete found for such ID").build();

		available.setStatus(true);
		available.setComment(complete.getComment());
		available.setDate(complete.getDate());
		available.setGrade(complete.getGrade());
		available.setHomework(complete.getHomework());
		Complete updatedComplete = completeService.update(available);
		EmailDetail emailDetail = new EmailDetail();
		if(updatedComplete.getGrade() >= 5) {
			emailDetail.setRecipient(updatedComplete.getLearner().getEmail());
			emailDetail.setFromName(updatedComplete.getItem().getChapter().getCourse().getInstructor().getName());
			emailDetail.setToName(updatedComplete.getLearner().getName());
			emailDetail.setCourseName(updatedComplete.getItem().getChapter().getCourse().getName());
			emailDetail.setItemName(updatedComplete.getItem().getItemName());
			emailDetail.setSubject("Response for Submission Grading");
			emailDetail.setMsgBody("aaa");
			emailService.sendMailTemplate(emailDetail, "to-learner");
		}else {
			emailDetail.setRecipient(updatedComplete.getLearner().getEmail());
			emailDetail.setFromName(updatedComplete.getItem().getChapter().getCourse().getInstructor().getName());
			emailDetail.setToName(updatedComplete.getLearner().getName());
			emailDetail.setCourseName(updatedComplete.getItem().getChapter().getCourse().getName());
			emailDetail.setItemName(updatedComplete.getItem().getItemName());
			emailDetail.setSubject("Response for Resubmit");
			emailDetail.setMsgBody("aaa");
			emailService.sendMailTemplate(emailDetail, "Resubmit");
		}
		if(updatedComplete != null)
			return ResponseEntity.ok(updatedComplete);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DeleteMapping("/deletecomplete/{completeID}")
	public ResponseEntity<Void> deleteComplete(@PathVariable int completeID){
		try{
			Complete complete = completeService.findById(completeID);
			if(complete == null) return ResponseEntity.notFound().header("message", "Complete not found. Delete failed").build();
			
			completeService.delete(completeID);
			return ResponseEntity.noContent().header("message", "Complete deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Complete deletion failed").build();
		}
	}
	
}
