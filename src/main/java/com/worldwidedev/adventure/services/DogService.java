package com.worldwidedev.adventure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldwidedev.adventure.models.Dog;
import com.worldwidedev.adventure.models.User;
import com.worldwidedev.adventure.repositories.DogRepository;

@Service
public class DogService {
	// prepare for dependency injection
	@Autowired
	private DogRepository dRepo;
//	public DogService(DogRepository repo) {
//		this.dRepo = repo;
//	}
	
	// getOne
	public Dog getOneDog(Long id) {
		Dog dog = this.dRepo.findById(id).orElse(null);
		return dog;
	}
	// getAll
	public List<Dog> getAllDogs() {
		return this.dRepo.findByOrderByNameDesc();
	}
	public List<Dog> getDogsByBreed(String breed) {
		return this.dRepo.findByBreedContainingOrderByNameDesc(breed);
	}
	public List<Dog> getDogsByState(String state) {
		return this.dRepo.findByTagState(state);
	}
	public List<Dog> getDogsByToyValue() {
		return this.dRepo.findByToysValue();
	}
	// createADog
	public Dog createDog(Dog newDog) {
		return this.dRepo.save(newDog);
	}
	// updateADog
	public Dog update(Dog dog) {
		return this.dRepo.save(dog);
	}
	public Dog createDog(String name, String breed, String description, String photo) {
		Dog newDog = new Dog(name, breed, description, photo);
		return this.dRepo.save(newDog);
	}
	public void deleteDog(Long id) {
		this.dRepo.deleteById(id);
	}
	public void addLiker(User user, Dog dog) {
		// get the list from the dog
		dog.getLikers().add(user);
		this.dRepo.save(dog);
	}
	public void removeLiker(User user, Dog dog) {
		dog.getLikers().remove(user);
		this.dRepo.save(dog);
	}
	// deleteADog
}
