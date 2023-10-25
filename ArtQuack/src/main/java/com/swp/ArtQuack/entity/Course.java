package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Utilities.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
@ToString
@EqualsAndHashCode

@Table(name = "Course")
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "courseID")
	private String courseID;
	
//	@Column(name = "instructorID")
//	private String instructorID;
//	
//	@Column(name = "cateID")
//	private String cateID;
//	
//	@Column(name = "levelID")
//	private String levelID;
//	
//	@Column(name = "reviewID")
//	private String reviewID;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "upload_date")
	private Date uplaod_date;
	
	@Column(name = "viewer")
	private int viewer;
	
	@Column(name = "rate", nullable = true)
//	@Size(min = Constants.REVIEW_MIN, max = Constants.REVIEW_MAX)
	private int rate;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Instructor.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "instructorID", referencedColumnName = "instructorID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Instructor instructor;
	
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "cateID", referencedColumnName = "cateID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Category category;
	
	@ManyToOne(targetEntity = Level.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "levelID", referencedColumnName = "levelID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Level level;
	
//	@OneToMany(targetEntity = Review.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "reviewID", referencedColumnName = "reviewID", nullable = false, insertable = true, updatable = false)
//	@JsonIgnore
//	@ToString.Exclude
//	private Review review;
	
	@OneToMany(targetEntity = Review.class, mappedBy = "course")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Review> reviewsList;
	
	@OneToMany(targetEntity = Chapter.class, mappedBy = "course")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Chapter> chaptersList;
	
	@OneToMany(targetEntity = Enrollment.class, mappedBy = "course")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Enrollment> enrollmentsList;

  

}
