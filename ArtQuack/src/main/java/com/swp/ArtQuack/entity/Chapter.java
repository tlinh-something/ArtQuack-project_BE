package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Table(name = "Chapter")
public class Chapter implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "chapterID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chapterID;
	
	@Column(name = "chapterName", nullable = false)
	private String chapterName;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "courseID", referencedColumnName = "courseID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Course course;

	@OneToMany(targetEntity = Item.class, mappedBy = "chapter")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Item> itemsList;
	
	@OneToMany(targetEntity = Submission.class, mappedBy = "chapter")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Submission> submissionsList;

	public int getChapterID() {
		return chapterID;
	}

	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Collection<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(Collection<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public Collection<Submission> getSubmissionsList() {
		return submissionsList;
	}

	public void setSubmissionsList(Collection<Submission> submissionsList) {
		this.submissionsList = submissionsList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
