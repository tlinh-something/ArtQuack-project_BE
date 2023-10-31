package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Integer>, JpaSpecificationExecutor<Learner>{
	
	public List<Learner> findByNameContainingIgnoreCaseAndStatusIsTrue(String name);
	
	public Learner findByLearnerIDAndStatusIsTrue(int learnerID);
	
	public Learner findByEmailAndPassword(String email, String password);
	
	public Learner findByName(String name);
	
	public List<Learner> findByStatusIsTrue();
	
	public List<Learner> findByStatusIsFalse();
}
