package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Instructor;
import java.util.List;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>, JpaSpecificationExecutor<Instructor>{

	public List<Instructor> findByNameIgnoreCaseAndStatusIsTrue(String name);
	
	public List<Instructor> findByStatusIsTrue();
	
	public List<Instructor> findByStatusIsFalse();
	
	public Instructor findByInstructorIDAndStatusIsTrue(int instructorID);
	
	public Instructor findByEmail(String email);
	
	public Instructor findByEmailAndPassword(String email, String password);
	
//	public int countByInstructorID(int instructorID);

}
