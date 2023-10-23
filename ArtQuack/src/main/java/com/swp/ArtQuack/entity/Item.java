package com.swp.ArtQuack.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

@Table(name = "Item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemID")
	private int itemID;
	
	@Column(name = "itemName", nullable = false)
	private String itemName;

//	@Column(name = "chapterID", nullable = false)
//	private int chapterID;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "status")
	private boolean status;
	
	//RELATIONSHIP SETUP
	@ManyToOne(targetEntity = Chapter.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "chapterID", referencedColumnName = "chapterID", nullable = false, insertable = true, updatable = false)
	@JsonIgnore
	@ToString.Exclude
	private Chapter chapter;
	
	@OneToMany(targetEntity = Complete.class, mappedBy = "item")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Complete> completesList;
}
