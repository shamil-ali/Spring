package com.qa.springtesting.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Tells Spring it's a table
public class Dog {
	
	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false)
	private Integer weight;
	
	// Constructors
	public Dog(Integer id, String name, Integer age, Integer weight) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

	public Dog() {
		super();
	}

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	// To-String
	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + "]";
	}

}
