package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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

	@Column(name = "rate")
	private int rate;

	@Column(name = "comment")
	private String comment;

	@Column(name = "date")
	private Date date;

	@Column(name = "status")
	private boolean status;

	@Column(name = "report")
	private String report;

	@Column(name = "typeofreport")
	private String typeOfReport;


	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Learner.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "learnerID", referencedColumnName = "learnerID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Learner learner;

	@ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "courseID", referencedColumnName = "courseID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Course course;

	@OneToMany(targetEntity = Transaction.class, mappedBy = "enrollment")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Transaction> transactionsList;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
