package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Complete;

@Repository
public interface CompleteRepository extends JpaRepository<Complete, Integer>, JpaSpecificationExecutor<Complete>{

	public Complete findByCompleteIDAndStatusIsTrue(int completeID);
	
	public List<Complete> findByStatusIsTrue();
	
	public  List<Complete>   findByItemItemID(int itemID);
	
	public List<Complete>  findByLearnerLearnerID(int learnerID);

	public  List<Complete> findByLearnerLearnerIDAndItemItemID(int learnerID, int itemID);
	
}
