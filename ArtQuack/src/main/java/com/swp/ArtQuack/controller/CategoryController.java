package com.swp.ArtQuack.controller;

import java.util.List;

import com.swp.ArtQuack.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/create-category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		Category savedCategory = categoryService.add(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}

	@PutMapping("/category/{categoryID}/update-category")
	public ResponseEntity<Category> updateCategory(@PathVariable int categoryID, @RequestBody Category category){
		Category available = categoryService.findById(category.getCateID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Category found for such ID").build();

		Category updatedCategory = categoryService.update(category);
		if(updatedCategory != null)
			return ResponseEntity.ok(updatedCategory);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@DeleteMapping("/category/{categoryID}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int categoryID){
		try{
			if(categoryService.findById(categoryID) == null)
				return ResponseEntity.notFound().header("message", "No Category found for such ID").build();

			if(categoryService.delete(categoryID))
				return ResponseEntity.noContent().header("message", "category deleted successfully").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "category deletion failed").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "category deletion failed").build();
		}
	}

}
