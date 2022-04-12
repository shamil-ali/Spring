package com.qa.springtesting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springtesting.domain.Dog;

@Repository
public interface DogRepo extends JpaRepository<Dog, Integer> {
	
	// SPRING WILL AUTO-GENERATE ALL OF THE CRUD FUNCTIONALITY
	
	List<Dog> findByNameIgnoreCase(String name);
	List<Dog> findByAge(Integer age);
	List<Dog> findByWeight(Integer weight);

}
