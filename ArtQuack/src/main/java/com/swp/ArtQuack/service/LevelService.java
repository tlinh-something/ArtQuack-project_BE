package com.swp.ArtQuack.service;

import java.util.List;

import com.swp.ArtQuack.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.repository.LevelRepository;

@Service
public class LevelService {

	@Autowired
	private LevelRepository levelRepository;
	
	public List<Level> findAll(){
		return levelRepository.findByStatusIsTrue();
	}
	
	public Level findById(int levelID) {
		return levelRepository.findByLevelID(levelID);
	}

	public List<Level> findDeletedLevel(){ return levelRepository.findByStatusIsFalse(); }

	//ADD
	public Level add(Level level){
		return levelRepository.save(level);
	}

	//UPDATE
	public Level update(Level level){
		return levelRepository.save(level);
	}

	//DELETE
	public boolean delete(int levelID) {
		Level level = findById(levelID);
		if(level == null) return false;
		level.setStatus(false);
		update(level);
		return !level.isStatus();
	}
}
