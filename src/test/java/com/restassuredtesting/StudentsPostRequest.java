package com.restassuredtesting;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;


/*
Different ways we can create request body
1. Hashmap
2. using org.json
3. using POJO(Plain Old Java Object)
4. using external json file
*/

public class StudentsPostRequest {
	
	int userId;
	
	//@Test(priority=1)	
	void testPostStudentHashMap()
	{	
		/*
		"id": 4,
	      "name": "Suchi",
	      "location": "Wakad",
	      "contact": "8275295754",
	      "courses": [
	        "Jmeter",
	        "API"
		*/
		
		HashMap<String,Object> mapData = new HashMap<String,Object>();
		mapData.put("name", "Suchi");
		mapData.put("location", "Wardha");
		mapData.put("contact", "8275295754");
		mapData.put("courses", new Object[] {"Jmeter","API"});
		
		userId=
				given()
					.contentType("application/json")
					.body(mapData)
			
				.when()
					.post("http://localhost:3000/students")
					.jsonPath().getInt("id");
		
		/*	
		.then()
			.statusCode(201)
			.body("name", equalTo("Suchi"))
			.body("location", equalTo("Wardha"))
			.body("contact", equalTo("8275295754"))
			.body("courses[0]", equalTo("Jmeter"))
			.body("courses[1]", equalTo("API"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		*/	
		
	}	
	

	
	//@Test(priority=1)
	void testPostStudentOrgJson()
	{
		/*
		"id": 4,
	      "name": "Suchi",
	      "location": "Wakad",
	      "contact": "8275295754",
	      "courses": [
	        "Jmeter",
	        "API"
		*/
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Suchi");
		jsonData.put("location", "Wardha");
		jsonData.put("contact", "8275295754");
		jsonData.put("courses", new Object[] {"Jmeter","API"});
		
		given()
			.contentType("application/json")
			.body(jsonData.toString())
			
		.when()
			.post("http://localhost:3000/students")
			//.jsonPath().getInt("id");
		
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Suchi"))
			.body("location", equalTo("Wardha"))
			.body("contact", equalTo("8275295754"))
			.body("courses[0]", equalTo("Jmeter"))
			.body("courses[1]", equalTo("API"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();		
	
	}
	
	//@Test(priority=1)
	void testPostStudentPOJO()
	{
		StudentPOJO pojoData = new StudentPOJO();
		pojoData.setName("Suchi");
		pojoData.setLocation("Wardha");
		pojoData.setContact("8275295754");
		pojoData.setCourses(new String[]{"Jmeter","API"});
		
		given()
		.contentType("application/json")
		.body(pojoData)
		
	.when()
		.post("http://localhost:3000/students")
		//.jsonPath().getInt("id");
	
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Suchi"))
		.body("location", equalTo("Wardha"))
		.body("contact", equalTo("8275295754"))
		.body("courses[0]", equalTo("Jmeter"))
		.body("courses[1]", equalTo("API"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();	
	
	}
	
	@Test(priority=1)
	void testPostStudentJSONFile() throws FileNotFoundException
	{
		
		File f = new File(".//body.json");
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jsonData = new JSONObject(jt);
		
		given()
		.contentType("application/json")
		.body(jsonData.toString())
		
	.when()
		.post("http://localhost:3000/students")
		//.jsonPath().getInt("id");
	
		
	.then()
		.statusCode(201)
		.body("name", equalTo("Suchi"))
		.body("location", equalTo("Wardha"))
		.body("contact", equalTo("8275295754"))
		.body("courses[0]", equalTo("Jmeter"))
		.body("courses[1]", equalTo("API"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();	
		
	}
	
	@Test(priority=2, dependsOnMethods="testPostStudentJSONFile")
	void deleteStudent()
	{	
		System.out.println(userId);
		
		when()
			.delete("http://localhost:3000/students/4")
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
}
