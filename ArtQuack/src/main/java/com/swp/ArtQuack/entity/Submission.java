package com.swp.ArtQuack.entity;

import java.io.Serializable;

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
@EqualsAndHashCode
@ToString

@Table(name = "Submission")
public class Submission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "submitID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int submitID;
	
	@Column(name = "final_project", nullable = false)
	private String final_project;

	@Column(name = "comment")
	private String comment;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Learner.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "learnerID", referencedColumnName = "learnerID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Learner learner;
	
	@ManyToOne(targetEntity = Chapter.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "chapterID", referencedColumnName = "chapterID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Chapter chapter;

	public int getSubmitID() {
		return submitID;
	}

	public void setSubmitID(int submitID) {
		this.submitID = submitID;
	}

	public String getFinal_project() {
		return final_project;
	}

	public void setFinal_project(String final_project) {
		this.final_project = final_project;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
