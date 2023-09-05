package com.restassuredtesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
	
	int userId;	
	
	/*
	 * RestAssured testcases by default follow BDD approach gerkin language -
	 * keywords/methods given() - Pre-requisites content-type,set cookies, add auth,
	 * add param, set headers etc when() - get, post, put, delete then() - Validate
	 * status code, extract response, extract headers, cookies and responseBody
	 */
	
	@Test(priority = 1)
	void getListOfUsers()
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
			.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();		//console print
					
	}
	
	@Test(priority = 2)
	void createUser()
	{	
		HashMap<Object, Object> mapData = new HashMap<Object, Object>();
		mapData.put("name", "padi");
		mapData.put("job", "QA");
		
		
		userId=given()
			.contentType("application/json")
			.body(mapData)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test(priority=3, dependsOnMethods = "createUser")
	void updateUser()
	{
		HashMap<Object, Object> mapData = new HashMap<Object, Object>();
		mapData.put("name", "ankita");
		mapData.put("job", "Development");
		
		
		given()
			.contentType("application/json")
			.body(mapData)
		
		.when()
			.put("https://reqres.in/api/users/"+userId)
			
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4, dependsOnMethods = "createUser")
	void deleteUse()
	{
		given()			
		
		.when()
			.delete("https://reqres.in/api/users/"+userId)			
		
		.then()
			.statusCode(204)
			.log().all();
	}
	
}
