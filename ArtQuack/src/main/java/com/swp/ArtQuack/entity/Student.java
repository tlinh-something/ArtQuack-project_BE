package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Table(name = "Student")
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "studentID")
	private String studentID;
	
	@Column(name = "name",unique = true, nullable = false)
//	@Size(min = Constants.USERNAME_MIN, message = "username must have at least 6 characters")
	private String name;
	
	@Column(name = "email",unique = true, nullable = false)
	private String email;
	
	@Column(name = "password",unique = true, nullable = false)
//	@Size(min = Constants.PASSWORD_MIN, message = "password must have at least 6 characters")
	private String password;
	
	@Column(name = "reviewID", nullable = false)
	private String reviewID;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "role")
	private String role;
	
	//RELATIONSHIP SETUP	
	@OneToMany(targetEntity = Enrollment.class, mappedBy = "student")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Enrollment> enrollmentsList;
	
	@OneToMany(targetEntity = Submission.class, mappedBy = "student")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Submission> submissionsList;
	
	@OneToMany(targetEntity = Review.class, mappedBy = "student")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Review> reviewsList;

}
