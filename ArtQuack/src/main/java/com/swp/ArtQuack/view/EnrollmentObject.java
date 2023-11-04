package com.swp.ArtQuack.view;

import java.util.Date;

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
public class EnrollmentObject {

	private int enrollmentID;
	private Date date;
	private int rate;
	private String comment;
	private boolean status;
	
	private int learnerID;
	private String learnerName;
	
	private int cateID;
	private String cateName;
	
	private int levelID;
	private String levelName;
	
	private int courseID;
	private String courseName;
	private String description;
	private Date upload_date;
	private int viewer;
	private int rateCourse;
	private String avatar;
	
	
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRateCourse() {
		return rateCourse;
	}
	public void setRateCourse(int rateCourse) {
		this.rateCourse = rateCourse;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public int getViewer() {
		return viewer;
	}
	public void setViewer(int viewer) {
		this.viewer = viewer;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
