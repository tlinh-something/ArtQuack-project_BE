package com.swp.ArtQuack.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Table(name = "Submission")
public class Submission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "submitID")
	private String submitID;
	
	@Column(name = "final_project", nullable = false)
	private String final_project;
	
//	@Column(name = "studentID", nullable = false)
//	private String studentID;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentID", referencedColumnName = "studentID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Student student;
	
	@ManyToOne(targetEntity = Chapter.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "chapterID", referencedColumnName = "chapterID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Chapter chapter;

}
