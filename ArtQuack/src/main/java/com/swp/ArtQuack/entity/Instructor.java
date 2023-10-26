package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.annotations.BatchSize;
import org.springframework.boot.convert.DataSizeUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Table(name = "Instructor")
public class Instructor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "instructorID")
	private String instructorID;

	@Column(name = "name", unique = true, nullable = false)
//	@Size(min = Constants.USERNAME_MIN, message = "username must have at least 6 characters")
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
//	@Size(min = Constants.PASSWORD_MIN, message = "password must have at least 6 characters")
	private String password;
	
	@Column(name = "certificate", nullable = false)
	private String certificate;
	
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

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
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

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
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
