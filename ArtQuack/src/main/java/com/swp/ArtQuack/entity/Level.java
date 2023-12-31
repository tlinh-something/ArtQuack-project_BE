package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	private String levelID;
	
	@Column(name = "levelName")
	private String levelName;
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "level")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Course> coursesList;

	public String getLevelID() {
		return levelID;
	}

	public void setLevelID(String levelID) {
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
