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
public class CompleteObject {

	private int completeID;
	private Date date;
	private boolean status;
	
	private int learnerID;
	private String learnerName;
	
	private int itemID;
	private String itemName;
	
	
	
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
	public int getCompleteID() {
		return completeID;
	}
	public void setCompleteID(int completeID) {
		this.completeID = completeID;
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
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
}
