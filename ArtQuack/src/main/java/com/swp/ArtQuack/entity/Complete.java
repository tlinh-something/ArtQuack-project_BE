package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Date;

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

@Table(name = "Complete")
public class Complete implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "completeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int completeID;

	@Column(name = "date")
	private Date date;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "homework")
	private String homework;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Item.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "itemID", referencedColumnName = "itemID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Item item;

	@ManyToOne(targetEntity = Learner.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "learnerID", referencedColumnName = "learnerID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Learner learner;

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

	public String getHomework() {
		return homework;
	}

	public void setHomework(String homework) {
		this.homework = homework;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}
}
