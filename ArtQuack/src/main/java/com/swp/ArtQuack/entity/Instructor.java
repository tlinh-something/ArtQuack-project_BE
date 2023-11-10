package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
//@EqualsAndHashCode

@Table(name = "Instructor")
public class Instructor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "instructorID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorID;

	@Column(name = "name", nullable = false)
//	@Size(min = Constants.USERNAME_MIN, message = "username must have at least 6 characters")
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
//	@Size(min = Constants.PASSWORD_MIN, message = "password must have at least 6 characters")
	private String password;
	
	@Column(name = "rate", precision = 2, scale = 1)
	private int rate;
	
	@Column(name = "summarize")
	private String summarize;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "role")
	private String role;
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "instructor")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Course> coursesList;

	@OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Wallet wallet;


	public int getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getSummarize() {
		return summarize;
	}

	public void setSummarize(String summarize) {
		this.summarize = summarize;
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
