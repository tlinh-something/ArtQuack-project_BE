package com.swp.ArtQuack.service;

import java.util.List;

import com.swp.ArtQuack.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepoService;
	
	//FIND
		public List<Category> findAll(){
			return categoryRepoService.findAll();
		}
		
		public Category findById(int id) {
			return categoryRepoService.findByCateID(id);
		}
		
		public List<Category> findByKeyword(String keyword){
			return categoryRepoService.findByCateNameContainingIgnoreCase(keyword.trim());
		}
		
	//ADD
	public Category add(Category category) {
		return categoryRepoService.save(category);
	}
	
	//UPDATE
	public Category update(Category category) {
		return categoryRepoService.save(category);
	}
	
	//DELETE
	public boolean delete(int categoryID) {
		Category category = findById(categoryID);
		if(category == null) return false;
		category.setStatus(false);
		update(category);
		return !category.isStatus();
	}
		
}
