package com.qa.springtesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springtesting.domain.Dog;
import com.qa.springtesting.service.DogService;

@RestController // TELLS SPRING THIS IS A CONTROLLER
// REST COMPLAINT  (REPRESENTATIONAL STATE TRANSFER)
public class DogController {
	
	private DogService service;
	
	@Autowired // Tell Spring to fetch Dog Service from the context - dependency injection
	public DogController(DogService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/helloWorld") // End point
	public String hello() {
		return "Hello World!";
	}
	
	// CRUD Functionality
	
	// CREATE
	@PostMapping("/create") // 201 - CREATED
	public ResponseEntity<Dog> createDog(@RequestBody Dog d) {
		Dog created = this.service.create(d);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(created, HttpStatus.CREATED);
		return response;
	}
	
	// READ ALL
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Dog>> getAllDoggos() {
		return ResponseEntity.ok(this.service.getAll());
	}
	
	// READ ONE
	@GetMapping("/get/{id}") // 200 - OK
	public Dog getDog(@PathVariable Integer id) {
		return this.service.getOne(id);
	}
	
	// UPDATE
	@PutMapping("/replace/{id}") // 202 - ACCEPTED
	public ResponseEntity<Dog> replaceDog(@PathVariable Integer id, @RequestBody Dog newDog) {
		Dog body = this.service.replace(id, newDog);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	// DELETE
	@DeleteMapping("/remove/{id}") // 204 - NO CONTENT
	public ResponseEntity<?> removeDog(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// READ BY NAME
	@GetMapping("/getByName/{name}") // 200 - OK
	public ResponseEntity<List<Dog>> getDogByName(@PathVariable String name) {
		List<Dog> found = this.service.getDoggosByName(name);
		return ResponseEntity.ok(found);
	}
	
	// READ BY AGE
	@GetMapping("/getByAge/{age}") // 200 - OK
	public ResponseEntity<List<Dog>> getDogByAge(@PathVariable Integer age) {
		List<Dog> found = this.service.getDoggosByAge(age);
		return ResponseEntity.ok(found);
	}
	
	// READ BY WEIGHT
	@GetMapping("/getByWeight/{weight}") // 200 - OK
	public ResponseEntity<List<Dog>> getDogByWeight(@PathVariable Integer weight) {
		List<Dog> found = this.service.getDoggosByWeight(weight);
		return ResponseEntity.ok(found);
	}
}
