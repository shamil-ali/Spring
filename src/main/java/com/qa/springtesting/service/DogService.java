package com.qa.springtesting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.springtesting.DogRepo;
import com.qa.springtesting.domain.Dog;

@Service // Stores the main business logic of the application
public class DogService implements ServiceIF<Dog>{
	
	private DogRepo repo;
	
	@Autowired
	public DogService(DogRepo repo) {
		super();
		this.repo = repo;
	}
	
	// CRUD Functionality
		
		// INSERT INTO Dog
		public Dog create(Dog d) {
			Dog created = this.repo.save(d);
			return created;
		}
		
		// SELECT * FROM Dog
		public List<Dog> getAll() {
			return this.repo.findAll();
		}
		
		// SELECT * FROM Dog WHERE ID =
		public Dog getOne(Integer id) {
			Optional<Dog> found = this.repo.findById(id);
			return found.get();
		}
		
		// UPDATE
		public Dog replace(Integer id, Dog newDog) {
			Dog existing = this.repo.findById(id).get();
			existing.setAge(newDog.getAge());
			existing.setWeight(newDog.getWeight());
			existing.setName(newDog.getName());
			Dog updated = this.repo.save(existing);
			return updated;
		}
		
		// DELETE * FROM Dog WHERE ID =
		public void remove(@PathVariable Integer id) {
			this.repo.deleteById(id);
		}
		
		// SELECT * FROM Dog WHERE name =
		public List<Dog> getDoggosByName(String name) {
			List<Dog> found = this.repo.findByNameIgnoreCase(name);
			return found;
		}
		
		// SELECT * FROM Dog WHERE age =
		public List<Dog> getDoggosByAge(Integer age) {
			List<Dog> found = this.repo.findByAge(age);
			return found;
		}
		
		// SELECT * FROM Dog WHERE weight =
		public List<Dog> getDoggosByWeight(Integer weight) {
			List<Dog> found = this.repo.findByWeight(weight);
			return found;
		}
}
