package com.swp.ArtQuack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.repository.ItemRepository;
import com.swp.ArtQuack.view.ChapterObject;
import com.swp.ArtQuack.view.ItemObject;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepoService;
	
	public List<Item> findAll(){
		return itemRepoService.findByStatusIsTrue();
	}
	
	public Item findById(int itemID) {
		return itemRepoService.findByItemIDAndAndStatusIsTrue(itemID);
	}
	
	public List<Item> findByChapterID(int chapterID){
		return itemRepoService.findByChapterChapterID(chapterID);
	}
	
	//ADD
	public Item add(Item item) {
		return itemRepoService.save(item);
	}
	
	//UPDATE
	public Item update(Item newItem) {
		return itemRepoService.save(newItem);
	}
	
	//DELETE
	public boolean delete(int itemID) {
		Item item = findById(itemID);
		if(item == null) return false;
		item.setStatus(false);
		update(item);
		return !item.isStatus();
	}
	
	//DISPLAY
	public ItemObject displayRender(Item x) {
		ItemObject object = new ItemObject();
		object.setItemID(x.getItemID());
		object.setItemName(x.getItemName());
		object.setContent(x.getContent());
		object.setStatus(x.isStatus());
		object.setReport(x.getReport());
		object.setTypeofreport(x.getTypeofreport());
		
		object.setChapterID(x.getChapter().getChapterID());
		object.setChapterName(x.getChapter().getChapterName());

		object.setCourseID(x.getChapter().getCourse().getCourseID());
		object.setCourseName(x.getChapter().getCourse().getName());

		return object;
	}
} 
