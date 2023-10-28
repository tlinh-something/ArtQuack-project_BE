package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Post;
import com.swp.ArtQuack.repository.PostRepository;
import com.swp.ArtQuack.view.InstructorObject;
import com.swp.ArtQuack.view.PostObject;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepoService;
	
	public List<Post> findAll(){
		return postRepoService.findAll();
	}
	
	public Post findById(int postID) {
		return postRepoService.findByPostIDAndStatusIsTrue(postID);
	}
	
	//ADD
	public Post create(Post post) {
		return postRepoService.save(post);
	}
	
	//UPDATE
	public Post udpate(Post newPost) {
		return postRepoService.save(newPost);
	}
	
	//DELETE
	public boolean delete(int postID) {
		postRepoService.deleteById(postID);
		return postRepoService.findById(postID).isEmpty();
	}
	
	//DISPLAY
	public List<PostObject> display(List<Post> ls){
		List<PostObject> list = new ArrayList<>();
		for(Post x: ls) {
			PostObject y = new PostObject();
			y.setPostID(x.getPostID());;
			y.setTitle(x.getTitle());
			y.setContent(x.getContent());
			y.setAuthor(x.getAuthor());
			y.setDate(x.getDate());
			y.setStatus(x.isStatus());
			
			y.setCateID(x.getCategory().getCateID());
			y.setCateName(x.getCategory().getCateName());
			
			list.add(y);
		}
		return list;
	}
}
