package com.swp.ArtQuack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer>, JpaSpecificationExecutor<Chapter>{

	public List<Chapter> findByCourseCourseID(int courseID);
	
	public List<Chapter> findByStatusIsTrue();
	
	public Chapter findByChapterNameContainingIgnoreCaseAndStatusIsTrue(String chapterName);
	
	public Chapter findByChapterIDAndStatusIsTrue(int chapterID);
}
