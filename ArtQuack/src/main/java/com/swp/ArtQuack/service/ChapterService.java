package com.swp.ArtQuack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.repository.ChapterRepository;
import com.swp.ArtQuack.view.ChapterObject;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepoService;
	
	public List<Chapter> findAll(){
		return chapterRepoService.findByStatusIsTrue();
	}
	
	public Chapter findById(int id) {
		Optional<Chapter> chapter = chapterRepoService.findById(id);
		if(chapter.isPresent()) return chapter.get();
		else return null;
	}
	
	public List<Chapter> findByCourseID(int courseID){
		return chapterRepoService.findByCourseCourseID(courseID);
	}
	
	public Chapter findByKeyword(String keyword){
		return chapterRepoService.findByChapterNameContainingIgnoreCaseAndStatusIsTrue(keyword);
	}
	
	//ADD
	public Chapter add(Chapter chapter) {
		return chapterRepoService.save(chapter);
	}
	
	//UPDATE
	public Chapter update(Chapter newChapter) {
		return chapterRepoService.save(newChapter);
	}
	
	//DELETE
	public boolean delete(int chapterID) {
			Chapter chapter = findById(chapterID);
			if(chapter == null) return false;
			chapter.setStatus(false);
			update(chapter);
			return !chapter.isStatus();
	}
	
	//DISPLAY
	public ChapterObject displayRender(Chapter x) {
			ChapterObject object = new ChapterObject();
			object.setChapterID(x.getChapterID());
			object.setChapterName(x.getChapterName());
			object.setStatus(x.isStatus());
			object.setCourseID(x.getCourse().getCourseID());
			object.setCourseName(x.getCourse().getName());

		return object;
	}
}
