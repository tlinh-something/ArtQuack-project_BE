package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	private String cateID;
	
	@Column(name = "cateName", nullable = false)
	private String cateName;
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "category")
	private Collection<Course> courseList;
	
	
}
