package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Table(name = "Category")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id
	@Column(name = "cateID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cateID;
	
	@Column(name = "cateName", nullable = false)
	private String cateName;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "picture")
	private String picture;
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "category")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Course> courseList;
	
	
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCateID() {
		return cateID;
	}

	public void setCateID(int cateID) {
		this.cateID = cateID;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Collection<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(Collection<Course> courseList) {
		this.courseList = courseList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
