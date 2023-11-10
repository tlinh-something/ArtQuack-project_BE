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
@ToString
@EqualsAndHashCode
@Table(name = "Learner")
public class Learner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "learnerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int learnerID;
	
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
	@OneToMany(targetEntity = Enrollment.class, mappedBy = "learner")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Enrollment> enrollmentsList;
	

	@OneToMany(targetEntity = Complete.class, mappedBy = "learner")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Complete> completesList;

	@OneToOne(mappedBy = "learner", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Wallet wallet;

	public int getLearnerID() {
		return learnerID;
	}

	public void setLearnerID(int learnerID) {
		this.learnerID = learnerID;
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

	public Collection<Complete> getCompletesList() {
		return completesList;
	}

	public void setCompletesList(Collection<Complete> completesList) {
		this.completesList = completesList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
