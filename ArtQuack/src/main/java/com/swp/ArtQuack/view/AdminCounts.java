package com.swp.ArtQuack.view;

public class AdminCounts {

	private long instrucCount;
	private long learnerCount;
	private long postCount;
	private long courseCount;
	public AdminCounts(long instrucCount, long learnerCount, long postCount, long courseCount) {
		super();
		this.instrucCount = instrucCount;
		this.learnerCount = learnerCount;
		this.postCount = postCount;
		this.courseCount = courseCount;
	}
	public long getInstrucCount() {
		return instrucCount;
	}
	public void setInstrucCount(long instrucCount) {
		this.instrucCount = instrucCount;
	}
	public long getLearnerCount() {
		return learnerCount;
	}
	public void setLearnerCount(long learnerCount) {
		this.learnerCount = learnerCount;
	}
	public long getPostCount() {
		return postCount;
	}
	public void setPostCount(long postCount) {
		this.postCount = postCount;
	}
	public long getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(long courseCount) {
		this.courseCount = courseCount;
	}
	
	
	
	
}
