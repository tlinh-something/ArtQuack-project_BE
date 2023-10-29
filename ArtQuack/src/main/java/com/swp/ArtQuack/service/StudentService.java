package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Review;
import com.swp.ArtQuack.entity.Student;
import com.swp.ArtQuack.repository.StudentRepository;
//import com.swp.ArtQuack.view.StudentObject;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepoService;
	
	//FIND
	public List<Student> findAll(){
		return studentRepoService.findAll();
	}
	
	public Student findById(int studentID) {
		return studentRepoService.findByStudentIDAndStatusIsTrue(studentID);
	}
	
	public List<Student> findByNameIgnorecase(String name){
		return studentRepoService.findByNameContainingIgnoreCaseAndStatusIsTrue(name.trim());
	}
	
	public Student login(String email, String password) {
		if(email == null || password == null) return null;
		return studentRepoService.findByEmailAndPassword(email, password);
	}
	
	public Student checkConflict(String name) {
		if(name == null) return null;
		return studentRepoService.findByName(name);
	}
	
	//ADD
	public Student add(Student student) {
		return studentRepoService.save(student);
	}
	
	//UPDATE
	public Student update(Student newStudent) {
		return add(newStudent);
	}
		
	//DELETE
	public boolean delete(int id) {
		Student student = findById(id);
		if(student == null) return false;
		student.setStatus(false);
		update(student);
		return !student.isStatus();
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
