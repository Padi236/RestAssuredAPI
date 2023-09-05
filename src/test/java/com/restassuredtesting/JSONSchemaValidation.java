package com.restassuredtesting;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JSONSchemaValidation {
	
	
	//https://jsonformatter.org/json-to-jsonschema
	//XML schema validation is not possible in Postman
	
	@Test
	void jsonSchemaValidation()
	{
		given()
		
		.when()
			.get("http://localhost:3000/students")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidation.json"));
	}

}
