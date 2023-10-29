package com.swp.ArtQuack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.service.LevelService;

@RestController
@RequestMapping("/api")
public class LevelController {

	@Autowired
	private LevelService levelService;
	
	@GetMapping("/levels")
	public List<Level> findAll(){
		return levelService.findAll();
	}
	
	
}
