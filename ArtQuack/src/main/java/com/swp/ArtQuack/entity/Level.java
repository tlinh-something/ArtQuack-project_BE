package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "Level")
public class Level implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "levelID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int levelID;
	
	@Column(name = "levelName")
	private String levelName;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "level")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Course> coursesList;

	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Collection<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(Collection<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
