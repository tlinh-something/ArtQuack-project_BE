package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.List;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.swp.ArtQuack.repository.InstructorRepository;
import com.swp.ArtQuack.view.InstructorObject;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepoService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Instructor> findAll(){
		return instructorRepoService.findByStatusIsTrue();
	}
	
	public List<Instructor> findInstructorsByTrue(){
		return instructorRepoService.findByStatusIsTrue();
	}
	
	public List<Instructor> findDeletedInstructor(){
		return instructorRepoService.findByStatusIsFalse();
	}
	
	public Instructor findById(int instructorID) {
		return instructorRepoService.findByInstructorIDAndStatusIsTrue(instructorID);
	}
	
	public List<Instructor> findByInstructorName(String name){
		return instructorRepoService.findByNameIgnoreCaseAndStatusIsTrue(name);
	}
	
	public Instructor login(String email, String password) {
		if(email == null || password == null) return null;
		return instructorRepoService.findByEmailAndPassword(email, password);
	}
	
	public Instructor checkConflict(String email) {
		if(email == null) return null;
		return instructorRepoService.findByEmail(email);
	}
	
	//ADD
	public Instructor add(Instructor instructor) {
		return instructorRepoService.save(instructor);
	}
	
	//UPDATE
	public Instructor update(Instructor newInstructor) {
			return instructorRepoService.save(newInstructor);
		}
		
	//DELETE
	public boolean delete(int id) {
		Instructor instructor = findById(id);
		if(instructor == null) return false;
		instructor.setStatus(false);
		update(instructor);
		return !instructor.isStatus();
	}
	
	//DISPLAY
		public List<InstructorObject> display(List<Instructor> ls){
			List<InstructorObject> list = new ArrayList<>();
			for(Instructor x: ls) {
				InstructorObject y = new InstructorObject();
				y.setInstructorID(x.getInstructorID());;
				y.setName(x.getName());
				y.setEmail(x.getEmail());
				y.setPassword(x.getPassword());
				y.setSummarize(x.getSummarize());
				y.setRate(x.getRate());
				y.setStatus(x.isStatus());
				y.setRole(x.getRole());
				
				list.add(y);
			}
			return list;
		}


	
}
