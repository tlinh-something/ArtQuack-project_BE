package com.swp.ArtQuack.view;

public class AdminCounts {

	private long instrucCount;
	private long learnerCount;
	private long courseCount;
	private long enrollmentCount;
	
	public AdminCounts(long instrucCount, long learnerCount,long courseCount, long enrollmentCount) {
		super();
		this.instrucCount = instrucCount;
		this.learnerCount = learnerCount;
		this.courseCount = courseCount;
		this.enrollmentCount = enrollmentCount;
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
	public long getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(long courseCount) {
		this.courseCount = courseCount;
	}
	public long getEnrollmentCount() {
		return enrollmentCount;
	}
	public void setEnrollmentCount(long enrollmentCount) {
		this.enrollmentCount = enrollmentCount;
	}
	
	
	
	
}
