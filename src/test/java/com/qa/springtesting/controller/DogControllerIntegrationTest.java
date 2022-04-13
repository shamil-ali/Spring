package com.qa.springtesting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springtesting.domain.Dog;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // Sets up theMockMvc object
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogControllerIntegrationTest {
	
	@Autowired // Pull the MockMvc object from the context
	private MockMvc mvc; // Class that performs the request (Similar to Postman)
	
	@Autowired
	private ObjectMapper mapper; // Java <-> JSON Converter that Spring uses
	
	@Test // CREATE
	void testCreate() throws Exception {
		Dog testDog = new Dog(null, "Lilo", 1, 30);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testCreatedDog = new Dog(3, "Lilo", 1, 30);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);		
	}
	
	@Test // GET ALL
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<Dog> testDoggos = List.of(new Dog(1, "Bruno", 15, 50), new Dog(2, "Chop", 10, 45));
		String testDoggosAsJSON = this.mapper.writeValueAsString(testDoggos);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDoggosAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test // GET ONE
	void testGetOne() throws Exception {
		RequestBuilder req = get("/get/1");
		
		String testGetOneAsJSON = this.mapper.writeValueAsString(new Dog(1, "Bruno", 15, 50));
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testGetOneAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test // REPLACE
	void testReplace() throws Exception {
		Dog testDog = new Dog(null, "Stitch", 1, 35);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testUpdatedDog = new Dog(1, "Stitch", 1, 35);
		String testUpdatedDogAsJSON = this.mapper.writeValueAsString(testUpdatedDog);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test // REMOVE
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());	
	}
	
	@Test // GET BY NAME
	void testGetByName() throws Exception {
		RequestBuilder req = get("/getByName/Bruno");
		
		List<Dog> testDogByName = List.of(new Dog(1, "Bruno", 15, 50));
		String testDogByNameAsJSON = this.mapper.writeValueAsString(testDogByName);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogByNameAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test // GET BY AGE
	void testGetByAge() throws Exception {
		RequestBuilder req = get("/getByAge/15");
		
		List<Dog> testDogByAge = List.of(new Dog(1, "Bruno", 15, 50));
		String testDogByAgeAsJSON = this.mapper.writeValueAsString(testDogByAge);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogByAgeAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test // GET BY WEIGHT
	void testGetByWeight() throws Exception {
		RequestBuilder req = get("/getByWeight/50");
		
		List<Dog> testDogByWeight = List.of(new Dog(1, "Bruno", 15, 50));
		String testDogByWeightAsJSON = this.mapper.writeValueAsString(testDogByWeight);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogByWeightAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
