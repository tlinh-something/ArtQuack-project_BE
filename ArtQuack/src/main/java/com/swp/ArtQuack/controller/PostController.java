package com.swp.ArtQuack.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Level;
import com.swp.ArtQuack.entity.Post;
import com.swp.ArtQuack.service.CategoryService;
import com.swp.ArtQuack.service.PostService;
import com.swp.ArtQuack.view.InstructorObject;
import com.swp.ArtQuack.view.PostObject;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostObject>> retrieveAllPosts(){
		List<Post> ls = postService.findAll();
		List<PostObject> list = postService.display(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/deletedposts")
	public ResponseEntity<List<PostObject>> retrieveAllDeletedPosts(){
		List<Post> ls = postService.findDeletedPost();
		List<PostObject> list = postService.display(ls);
		return ResponseEntity.ok(list);
    }
	
	@GetMapping("/post/{postID}")
	public ResponseEntity<PostObject> retrievePost(@PathVariable int postID) {
		Post post = postService.findById(postID);
		if(post != null) {
			List<Post> ls = new ArrayList<>();
			ls.add(post);
			List<PostObject> list = postService.display(ls);
			return ResponseEntity.status(HttpStatus.OK).body(list.get(0));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/category/{cateID}/post")
	public ResponseEntity<Post> createCourse(@PathVariable int cateID, @RequestBody Post post){
		try {
			
			Category category = categoryService.findById(cateID);
			if(category == null) return ResponseEntity.notFound().header("message", "Category not found. Adding failed").build();
			
			if(postService.findById(post.getPostID()) != null) 
				return ResponseEntity.badRequest().header("message", "Post with such ID already exists").build();
			
			post.setCategory(category);
			post.setStatus(true);
			Post savedPost = postService.create(post);
			if(savedPost != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
			else return ResponseEntity.internalServerError().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "Failed to add new post").build();
		}
	}
	
	@PutMapping("/post/{postID}/updatepost")
	public ResponseEntity<Post> updatePost(@PathVariable int postID, @RequestBody Post post){
		Post available = postService.findById(post.getPostID());
		if(available == null)
			return  ResponseEntity.notFound().header("message", "No Post found for such ID").build();
		
		post.setAuthor(available.getAuthor());
		
		Post updatedPost = postService.udpate(post);
		if(updatedPost != null)
			return ResponseEntity.ok(updatedPost);
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DeleteMapping("/post/{postID}")
	public ResponseEntity<Void> deletePost(@PathVariable int postID){
		try{
			if(postService.findById(postID) == null)
			return ResponseEntity.notFound().header("message", "No Post found for such ID").build();
		
			if(postService.delete(postID))
				return ResponseEntity.noContent().header("message", "post deleted successfully").build();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "post deletion failed").build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("message", "post deletion failed").build();
		}
	}
	
}
