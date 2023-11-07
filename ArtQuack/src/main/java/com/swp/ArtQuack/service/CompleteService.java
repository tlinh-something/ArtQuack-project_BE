package com.swp.ArtQuack.service;

import java.util.List;

import com.swp.ArtQuack.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Complete;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.repository.CompleteRepository;
import com.swp.ArtQuack.view.CompleteObject;
import com.swp.ArtQuack.view.ItemObject;

@Service
public class CompleteService {

	@Autowired
	private CompleteRepository completeRepoService;
	
	public List<Complete> findAll(){
		return completeRepoService.findByStatusIsTrue();
	}
	
	public Complete findById(int completeID) {
		return completeRepoService.findByCompleteIDAndStatusIsTrue(completeID);
	}
	
	public List<Complete> findByItemId(int itemID){
		return completeRepoService.findByItemItemID(itemID);
	}
	
	public List<Complete> findByLearnerId(int learnerID){
		return completeRepoService.findByLearnerLearnerID(learnerID);
	}
	
	public List<Complete> findByLearnerIDAndItemID(int learnerID, int itemID) {
		return completeRepoService.findByLearnerLearnerIDAndItemItemID(learnerID, itemID);
	}
	
	
	//ADD
	public Complete add(Complete complete) {
		return completeRepoService.save(complete);
	}
	
	//UPDATE
	public Complete update(Complete newComplete) {
		return completeRepoService.save(newComplete);
	}
	
	//DELETE
	public boolean delete(int completeID) {
		Complete complete = findById(completeID);
		if(complete == null) return false;
		complete.setStatus(false);
		update(complete);
		return !complete.isStatus();
	}
	
	//DISPLAY
	public CompleteObject displayRender(Complete x) {
		CompleteObject object = new CompleteObject();
		object.setCompleteID(x.getCompleteID());
		object.setDate(x.getDate());
		object.setStatus(x.isStatus());
		object.setComment(x.getComment());
		object.setGrade(x.getGrade());
		object.setHomework(x.getHomework());
		
		object.setLearnerID(x.getLearner().getLearnerID());
		object.setLearnerName(x.getLearner().getName());
		
		object.setItemID(x.getItem().getItemID());
		object.setItemName(x.getItem().getItemName());

		object.setInstructorID(x.getItem().getChapter().getCourse().getInstructor().getInstructorID());
		object.setInstructorName(x.getItem().getChapter().getCourse().getInstructor().getName());

		return object;
	}
 }
