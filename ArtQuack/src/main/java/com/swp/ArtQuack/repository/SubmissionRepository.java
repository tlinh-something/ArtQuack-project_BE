package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Submission;
import java.util.List;
import com.swp.ArtQuack.entity.Chapter;



@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer>, JpaSpecificationExecutor<Submission>{

	public Submission findBySubmitIDAndStatusIsTrue(int submitID);
	
	public List<Submission> findByChapterChapterID(int chapterID);
	
	public List<Submission> findByLearnerLearnerID(int learnerID);
	
	public List<Submission> findByStatusIsTrue();
	
}
