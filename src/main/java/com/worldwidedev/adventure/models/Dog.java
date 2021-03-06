package com.worldwidedev.adventure.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;
import com.worldwidedev.adventure.controllers.DogController;

@Entity
@Table(name="dogs")
public class Dog {
	// id (primary key)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Size(min=2,max=20, message="Default Name Message")
	@NotNull
	private String name;
	@NotNull
	private String breed;
	@Column(nullable = true, length = 64)
	private String photo;
	@NotNull
	@Size(min=5,max=255)
	private String description;
	@OneToOne(mappedBy="dog", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Tag tag;
	@OneToMany(mappedBy="dog", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Toy> toys;
	@ManyToMany 
	@JoinTable(
		name="likes",
		joinColumns= @JoinColumn(name="dog_id"),
		inverseJoinColumns= @JoinColumn(name="user_id")
	)
	private List<User> likers;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	public String getToysTotalValue() {
		Double sum = 0.0;
		for(Toy t : this.toys) {
			sum += t.getPrice();
		}
		return String.format("$%.2f", sum);
	}
	
	public Dog(String name, String breed, String description, String photo) {
		this.name = name;
		this.breed = breed;
		this.description = description;
		this.photo = photo;
	}
	
	public Dog() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getPhoto() {
		return photo;
	}

	public String getPhotoPath() {
		if(photo == null || id == null) { return null; }
		return "/user-photos/" + id + "/" + photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Toy> getToys() {
		return toys;
	}

	public void setToys(List<Toy> toys) {
		this.toys = toys;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getLikers() {
		return likers;
	}
	// WARNING: Convention Breaker...
//	public void addLiker(User liker) {
//		this.likers.add(liker);
//	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
}
