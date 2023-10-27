package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>{

}
