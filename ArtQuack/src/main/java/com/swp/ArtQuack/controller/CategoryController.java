package com.swp.ArtQuack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public List<Category> retrieveAllCategories(){
		return categoryService.findAll();
	}
	
	@GetMapping("/category/{cateID}")
	public ResponseEntity<Category> retrieveCategory(@PathVariable int cateID) {
		Category category = categoryService.findById(cateID);
		if(category != null) {
			return ResponseEntity.status(HttpStatus.OK).body(category);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/categories/{keyword}")
	public ResponseEntity<List<Category>> retrieveCategoriesByKeyword(@PathVariable String keyword){
		return ResponseEntity.ok(categoryService.findByKeyword(keyword));
    }
	
	
}
