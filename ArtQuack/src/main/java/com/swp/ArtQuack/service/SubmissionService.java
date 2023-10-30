package com.swp.ArtQuack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.entity.Submission;
import com.swp.ArtQuack.repository.SubmissionRepository;
import com.swp.ArtQuack.view.ItemObject;
import com.swp.ArtQuack.view.SubmissionObject;

@Service
public class SubmissionService {

	@Autowired
	private SubmissionRepository submissionRepoService;
	
	public List<Submission> findAll(){
		return submissionRepoService.findByStatusIsTrue();
	}
	
	public Submission findBySubmitID(int submitID) {
		return submissionRepoService.findBySubmitIDAndStatusIsTrue(submitID);
	}
	
	public List<Submission> findByChapterID(int chapterID){
		return submissionRepoService.findByChapterChapterID(chapterID);
	}
	
	public List<Submission> findByStudentID(int studentID){
		return submissionRepoService.findByStudentStudentID(studentID);
	}
	
	//ADD
	public Submission add(Submission submission) {
		return submissionRepoService.save(submission);
	}
	
	//UPDATE
	public Submission update(Submission newSubmission) {
		return submissionRepoService.save(newSubmission);
	}
	
	//DELETE
	public boolean delete(int submitID) {
		Submission submit = findBySubmitID(submitID);
		if(submit == null) return false;
		submit.setStatus(false);
		update(submit);
		return !submit.isStatus();
	}
	
	//DISPLAY
	public SubmissionObject displayRender(Submission x) {
		SubmissionObject object = new SubmissionObject();
		object.setSubmitID(x.getSubmitID());
		object.setFinal_project(x.getFinal_project());
		object.setGrade(x.getGrade());
		object.setComment(x.getComment());
		object.setStatus(x.isStatus());
		
		object.setStudentID(x.getStudent().getStudentID());
		object.setStudentName(x.getStudent().getName());
		
		object.setChapterID(x.getChapter().getChapterID());
		object.setChapterName(x.getChapter().getChapterName());

		return object;
	}
}
