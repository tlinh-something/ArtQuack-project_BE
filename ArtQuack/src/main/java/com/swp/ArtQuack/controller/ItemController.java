package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.swp.ArtQuack.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.service.ChapterService;
import com.swp.ArtQuack.service.ItemService;
import com.swp.ArtQuack.view.ChapterObject;
import com.swp.ArtQuack.view.ItemObject;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ChapterService chapterService;
	
	@GetMapping("items")
	public ResponseEntity<List<ItemObject>> retrieveAllItems() {
		List<ItemObject> ls = new ArrayList<ItemObject>();
		List<Item> itemList = itemService.findAll();
		for(Item x: itemList) {
			ls.add(itemService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@GetMapping("/item/{itemID}")
	public ResponseEntity<ItemObject> retrieveItem(@PathVariable int itemID) {
		Item item = itemService.findById(itemID);
		if (item != null) {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.displayRender(item));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/chapter/{chapterID}/items")
	public ResponseEntity<List<ItemObject>> findByChapterID(@PathVariable("chapterID") int chapterID){
		Chapter chapter = chapterService.findById(chapterID);
		if(chapter == null)
			return ResponseEntity.notFound().header("message", "No Chapter found for such ID").build();
		List<ItemObject> ls = new ArrayList<ItemObject>();
		List<Item> itemList = itemService.findByChapterID(chapterID);
		for(Item x: itemList) {
			ls.add(itemService.displayRender(x));
		}
		return ResponseEntity.ok(ls);
	}
	
	@PostMapping("/chapter/{chapterID}/createitem")
	public ResponseEntity<Item> createItem(@PathVariable int chapterID, @RequestBody Item item){
		try {
			Chapter chapter = chapterService.findById(chapterID);
			if(chapter == null) return ResponseEntity.notFound().header("message", "Chapter not found. Adding failed").build();

			if(itemService.findById(item.getItemID()) != null)
				return ResponseEntity.badRequest().header("message", "Item with such ID already exists").build();

			item.setChapter(chapter);
			item.setStatus(true);
			Item savedItem = itemService.add(item);
			if(savedItem != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new item").build();
		}
	}
	
	@PutMapping("/item/{itemID}/updateitem")
	public ResponseEntity<Item> updateItem(@PathVariable("itemID") int itemID , @RequestBody Item item){
		Item available = itemService.findById(item.getItemID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Item found for such ID").build();

		item.setStatus(available.isStatus());
		Item updatedItem = itemService.update(item);
		if(updatedItem != null)
			return ResponseEntity.ok(updatedItem);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DeleteMapping("/deleteitem/{itemID}")
	public ResponseEntity<Void> deleteItem(@PathVariable int itemID){
		try{
			Item item = itemService.findById(itemID);
			if(item == null) return ResponseEntity.notFound().header("message", "Item not found. Delete failed").build();
			
			itemService.delete(itemID);
			return ResponseEntity.noContent().header("message", "Item deleted successfully").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Item deletion failed").build();
		}
	}
	
}
