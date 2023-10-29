package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>{
	
	public List<Student> findByNameContainingIgnoreCaseAndStatusIsTrue(String name);
	
	public Student findByStudentIDAndStatusIsTrue(int studentID);
	
	public Student findByEmailAndPassword(String email, String password);
	
	public Student findByName(String name);
}
