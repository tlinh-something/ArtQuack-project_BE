package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentID;
	
	@Column(name = "name", nullable = false)
//	@Size(min = Constants.USERNAME_MIN, message = "username must have at least 6 characters")
	private String name;
	
	@Column(name = "email",unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
//	@Size(min = Constants.PASSWORD_MIN, message = "password must have at least 6 characters")
	private String password;
	
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

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<Enrollment> getEnrollmentsList() {
		return enrollmentsList;
	}

	public void setEnrollmentsList(Collection<Enrollment> enrollmentsList) {
		this.enrollmentsList = enrollmentsList;
	}

	public Collection<Submission> getSubmissionsList() {
		return submissionsList;
	}

	public void setSubmissionsList(Collection<Submission> submissionsList) {
		this.submissionsList = submissionsList;
	}

	public Collection<Review> getReviewsList() {
		return reviewsList;
	}

	public void setReviewsList(Collection<Review> reviewsList) {
		this.reviewsList = reviewsList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
