package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp.ArtQuack.Enum.CourseStatus;
import com.swp.ArtQuack.Utilities.Constants;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@ToString
@EqualsAndHashCode

@Table(name = "Course")
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "courseID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseID;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "upload_date")
	private Date upload_date;
	
	@Column(name = "viewer")
	private int viewer;
	
	@Column(name = "rate", precision = 2, scale = 1)
//	@Size(min = Constants.REVIEW_MIN, max = Constants.REVIEW_MAX)
	private int rate;

	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus = CourseStatus.UPDATING;
	
	@Column(name = "status", nullable = false)
	private boolean status;
	
	@Column(name = "Avatar")
	private String avatar;
	
	@Column(name = "price")
	private float price;

	@Column(name = "reason")
	private String reason;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Instructor.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "instructorID", referencedColumnName = "instructorID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Instructor instructor;
	
	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "cateID", referencedColumnName = "cateID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Category category;
	
	@ManyToOne(targetEntity = Level.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "levelID", referencedColumnName = "levelID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Level level;
	
	@OneToMany(targetEntity = Chapter.class, mappedBy = "course")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Chapter> chaptersList;
	
	@OneToMany(targetEntity = Enrollment.class, mappedBy = "course")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Enrollment> enrollmentsList;

//	@OneToMany(targetEntity = Transaction.class, mappedBy = "course")
//	@JsonIgnore
//	@ToString.Exclude
//	private Collection<Transaction> transactionsList;

	

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

	public String getName() {
		return name;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Collection<Chapter> getChaptersList() {
		return chaptersList;
	}

	public void setChaptersList(Collection<Chapter> chaptersList) {
		this.chaptersList = chaptersList;
	}

	public Collection<Enrollment> getEnrollmentsList() {
		return enrollmentsList;
	}

	public void setEnrollmentsList(Collection<Enrollment> enrollmentsList) {
		this.enrollmentsList = enrollmentsList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
