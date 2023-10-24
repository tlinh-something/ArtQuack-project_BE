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
	
	//RELATIONSHIP SETUP
	@OneToMany(targetEntity = Course.class, mappedBy = "instructor")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Course> coursesList;
}
