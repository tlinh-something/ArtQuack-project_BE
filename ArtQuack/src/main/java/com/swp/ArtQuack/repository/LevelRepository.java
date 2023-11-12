package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Level;
import java.util.List;


@Repository
public interface LevelRepository extends JpaRepository<Level, Integer>, JpaSpecificationExecutor<Level>{

	public Level findByLevelID(int levelID);

	public List<Level> findByStatusIsTrue();

	public List<Level> findByStatusIsFalse();


}
