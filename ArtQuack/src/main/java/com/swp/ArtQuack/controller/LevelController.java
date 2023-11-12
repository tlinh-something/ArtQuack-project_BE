package com.swp.ArtQuack.controller;

import java.util.List;

import com.swp.ArtQuack.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/level/{levelID}")
	public Level findByID(@PathVariable int levelID){
		return levelService.findById(levelID);
	}

	@PostMapping("/create-level")
	public ResponseEntity<Level> createlevel(@RequestBody Level level){
		Level savedLevel = levelService.add(level);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLevel);
	}

	@PutMapping("/level/{levelID}/update-level")
	public ResponseEntity<Level> updateLevel(@PathVariable int levelID, @RequestBody Level level){
		Level available = levelService.findById(level.getLevelID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Level found for such ID").build();

		Level updatedLevel = levelService.update(level);
		if(updatedLevel != null)
			return ResponseEntity.ok(updatedLevel);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@DeleteMapping("/level/{levelID}")
	public ResponseEntity<Void> deleteLevel(@PathVariable int levelID){
		try{
			if(levelService.findById(levelID) == null)
				return ResponseEntity.notFound().header("message", "No Level found for such ID").build();

			if(levelService.delete(levelID))
				return ResponseEntity.noContent().header("message", "level deleted successfully").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "level deletion failed").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "level deletion failed").build();
		}
	}
}
