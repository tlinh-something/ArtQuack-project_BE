package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.entity.Student;
import com.swp.ArtQuack.entity.Submission;
import com.swp.ArtQuack.service.ChapterService;
import com.swp.ArtQuack.service.StudentService;
import com.swp.ArtQuack.service.SubmissionService;
import com.swp.ArtQuack.view.ItemObject;
import com.swp.ArtQuack.view.SubmissionObject;

@RestController
@RequestMapping("/api")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/submissions")
	public ResponseEntity<List<SubmissionObject>> retrieveAllSubmissions() {
		List<SubmissionObject> ls = new ArrayList<SubmissionObject>();
		List<Submission> submitList = submissionService .findAll();
		for(Submission x: submitList) {
			ls.add(submissionService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/submission/{submitID}")
	public ResponseEntity<SubmissionObject> retrieveSubmission(@PathVariable int submitID) {
		Submission submit = submissionService.findBySubmitID(submitID);
		if (submit != null) {
			return ResponseEntity.status(HttpStatus.OK).body(submissionService.displayRender(submit));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/chapter/{chapterID}/submissions")
	public ResponseEntity<List<SubmissionObject>> findByChapterID(@PathVariable("chapterID") int chapterID){
		Chapter chapter = chapterService.findById(chapterID);
		if(chapter == null)
			return ResponseEntity.notFound().header("message", "No Chapter found for such ID").build();
		List<SubmissionObject> ls = new ArrayList<SubmissionObject>();
		List<Submission> submitList = submissionService.findByChapterID(chapterID);
		for(Submission x: submitList) {
			ls.add(submissionService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/student/{studentID}/submissions")
	public ResponseEntity<List<SubmissionObject>> findByStudentID(@PathVariable("studentID") int studentID){
		Student student = studentService.findById(studentID);
		if(student == null)
			return ResponseEntity.notFound().header("message", "No Student found for such ID").build();
		List<SubmissionObject> ls = new ArrayList<SubmissionObject>();
		List<Submission> submitList = submissionService.findByStudentID(studentID);
		for(Submission x: submitList) {
			ls.add(submissionService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@PostMapping("/student/{studentID}/chapter/{chapterID}/createsubmission")
	public ResponseEntity<Submission> createSubmission(@PathVariable int chapterID,@PathVariable int studentID, @RequestBody Submission submit){
		try {
			Student student = studentService.findById(studentID);
			if(student == null) return ResponseEntity.notFound().header("message", "Student not found. Adding failed").build();
			
			Chapter chapter = chapterService.findById(chapterID);
			if(chapter == null) return ResponseEntity.notFound().header("message", "Chapter not found. Adding failed").build();

			if(submissionService.findBySubmitID(submit.getSubmitID()) != null)
				return ResponseEntity.badRequest().header("message", "Submission with such ID already exists").build();

			submit.setStudent(student);
			submit.setChapter(chapter);
			submit.setStatus(true);
			Submission savedSubmit = submissionService.add(submit);
			if(savedSubmit != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmit);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new submission").build();
		}
	}
	
	@DeleteMapping("/deletesubmission/{submitID}")
	public ResponseEntity<Void> deleteSubmission(@PathVariable int submitID){
		try{
			Submission submit = submissionService.findBySubmitID(submitID);
			if(submit == null) return ResponseEntity.notFound().header("message", "Submission not found. Delete failed").build();
			
			submissionService.delete(submitID);
			return ResponseEntity.noContent().header("message", "Submission deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Submission deletion failed").build();
		}
	}
}
