package com.swp.ArtQuack.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Table(name = "Enrollment")
public class Enrollment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollmentID")
	private int enrollmentID;	
	
//	@Column(name = "studentID", nullable = false)
//	private int studentID;
//	
//	@Column(name = "courseID", nullable = false)
//	private int courseID;
	
	@Column(name = "status")
	private boolean status;
	
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "studentID", referencedColumnName = "studentID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Student student;
	
	@ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "courseID", referencedColumnName = "courseID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Course course;
	
}
