package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, String>{

}
