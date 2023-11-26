package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Item;
import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>{

	public Item findByItemIDAndAndStatusIsTrue(int itemID);
	
	public List<Item> findByChapterChapterID(int chapterID);
	
	public List<Item> findByStatusIsTrue();


}
