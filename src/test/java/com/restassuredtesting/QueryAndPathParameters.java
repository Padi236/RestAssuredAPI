package com.restassuredtesting;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class QueryAndPathParameters {
	
	@Test
	void testQueryAndPathPara()
	{
		//http://localhost:3000/students?name=ankita&location=Pimpri
		Response res = given()
			.contentType(ContentType.JSON)	
			.pathParam("mypath", "students")
			.queryParam("name","ankita")
			.queryParam("location", "Pimpri")
		
		.when()
			.get("http://localhost:3000/{mypath}");
			
		
		
		JSONArray jsonarray = new JSONArray(res.asString());
		for (int i = 0; i < jsonarray.length(); i++) {
		    JSONObject jsonobject = jsonarray.getJSONObject(i);
		    String name = jsonobject.getString("name");
		    String location = jsonobject.getString("location");
		    System.out.println(name+" : "+location);
		}
		
		
	}

}
