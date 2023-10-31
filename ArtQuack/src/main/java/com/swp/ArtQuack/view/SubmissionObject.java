package com.swp.ArtQuack.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class SubmissionObject {

	private int submitID;
	private String final_project;
	private String comment;
	private int grade;
	private boolean status;
	
	private int learnerID;
	private String learnerName;
	
	private int chapterID;
	private String chapterName;
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
	public int getLearnerID() {
		return learnerID;
	}
	public void setLearnerID(int learnerID) {
		this.learnerID = learnerID;
	}
	public String getLearnerName() {
		return learnerName;
	}
	public void setLearnerName(String learnerName) {
		this.learnerName = learnerName;
	}
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
	
	
}
