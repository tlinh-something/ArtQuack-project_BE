package com.swp.ArtQuack.view.response;

import lombok.Data;

import java.util.List;
@Data
public class CourseResponse {
    int courseID;
    String courseName;
    List<ChapterResponse> chapters;
	boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
	public List<ChapterResponse> getChapters() {
		return chapters;
	}
	public void setChapters(List<ChapterResponse> chapters) {
		this.chapters = chapters;
	}
    
    
}
