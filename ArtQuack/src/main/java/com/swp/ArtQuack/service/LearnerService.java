package com.swp.ArtQuack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.repository.LearnerRepository;
//import com.swp.ArtQuack.view.StudentObject;

@Service
public class LearnerService {

	@Autowired
	private LearnerRepository studentRepoService;
	
	//FIND
	public List<Learner> findAll(){
		return studentRepoService.findByStatusIsTrue();
	}
	
	public List<Learner> findAllDeletedStudents(){
		return studentRepoService.findByStatusIsFalse();
	}
	
	public Learner findById(int learnerID) {
		return studentRepoService.findByLearnerIDAndStatusIsTrue(learnerID);
	}
	
	public List<Learner> findByNameIgnorecase(String name){
		return studentRepoService.findByNameContainingIgnoreCaseAndStatusIsTrue(name.trim());
	}
	
	public Learner login(String email, String password) {
		if(email == null || password == null) return null;
		return studentRepoService.findByEmailAndPassword(email, password);
	}
	
	public Learner checkConflict(String name) {
		if(name == null) return null;
		return studentRepoService.findByName(name);
	}
	
	//ADD
	public Learner add(Learner student) {
		return studentRepoService.save(student);
	}
	
	//UPDATE
	public Learner update(Learner newStudent) {
		return add(newStudent);
	}
		
	//DELETE
	public boolean delete(int id) {
		Learner learner = findById(id);
		if(learner == null) return false;
		learner.setStatus(false);
		update(learner);
		return !learner.isStatus();
	}
	
//	//DISPLAY
//		public List<StudentObject> display(List<Student> ls){
//			List<StudentObject> list = new ArrayList<>();
//			for(Student x: ls) {
//				StudentObject y = new StudentObject();
//				y.setStudentID(x.getStudentID());
//				y.setName(x.getName());
//				y.setEmail(x.getEmail());
//				y.setPassword(x.getPassword());
//				y.setStatus(x.isStatus());
//				y.setRole(x.getRole());
//				
//				list.add(y);
//			}
//			return list;
//		}
}
