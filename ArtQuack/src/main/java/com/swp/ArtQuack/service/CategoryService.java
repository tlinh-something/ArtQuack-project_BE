package com.swp.ArtQuack.service;

import java.util.List;

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
		
		public Category findById(String id) {
			return categoryRepoService.findByCateID(id);
		}
		
		public List<Category> findByKeyword(String keyword){
			return categoryRepoService.findByCateNameContainingIgnoreCase(keyword.trim());
		}
		
}
