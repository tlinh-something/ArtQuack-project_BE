package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Post;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post>{

	public Post findByPostIDAndStatusIsTrue(int postID);
	
	public List<Post> findByStatusIsTrue();
	
	public List<Post> findByStatusIsFalse();
	
	
}
