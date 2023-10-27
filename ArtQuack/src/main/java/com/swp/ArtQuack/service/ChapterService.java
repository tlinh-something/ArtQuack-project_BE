package com.swp.ArtQuack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Review;
import com.swp.ArtQuack.repository.ChapterRepository;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepoService;
	
	public List<Chapter> findAll(){
		return chapterRepoService.findAll();
	}
	
	public Chapter findById(int id) {
		Optional<Chapter> chapter = chapterRepoService.findById(id);
		if(chapter.isPresent()) return chapter.get();
		else return null;
	}
	
	public List<Chapter> findByCourseID(int courseID){
		return chapterRepoService.findByCourseCourseID(courseID);
	}
}
