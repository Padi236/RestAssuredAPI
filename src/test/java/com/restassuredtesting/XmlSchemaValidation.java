package com.restassuredtesting;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class XmlSchemaValidation {

	@Test
	void xmlSchemaValidation()
	{
		
		//https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=2")
		
			.then()
			.statusCode(200)
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchemaValidation.xsd"));
	}
}
