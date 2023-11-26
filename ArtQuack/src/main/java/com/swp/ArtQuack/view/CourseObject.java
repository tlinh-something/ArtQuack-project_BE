package com.swp.ArtQuack.view;

import java.io.Serializable;
import java.util.Date;

import com.swp.ArtQuack.Enum.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class CourseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int courseID;
	private String name;
	private String description;
	private Date upload_date;
	private int viewer;
	private int rate;
	private CourseStatus courseStatus;
	private String avatar;
	private float price;
	private String reason;
	private boolean isEnrolled = false;
	
	//Instructor
	private int instructorID;
	private String instructorName;
	
	//Category
	private int cateID;
	private String cateName;
	
	//Level
	private int levelID;
	private String levelName;

	//Chapter
	private int chapterID;
	private String chapterName;

	//Item
	private int itemID;
	private String itemName;
	private String content;
	
//	//Review
//	private String reviewID;
//	private int rateReview;
//	
//	//Chapter
//	private String chapterID;
//	private String chapterName;


	public int getChapterID() {
		return chapterID;
	}

	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setEnrolled(boolean enrolled) {
		isEnrolled = enrolled;
	}

	public String getName() {
		return name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public int getInstructorID() {
		return instructorID;
	}
	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
