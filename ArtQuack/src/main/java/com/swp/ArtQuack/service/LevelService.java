package com.swp.ArtQuack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.repository.LevelRepository;

@Service
public class LevelService {

	@Autowired
	private LevelRepository levelRepository;
	
	public Level findById(String levelID) {
		return levelRepository.findByLevelID(levelID);
	}
}
