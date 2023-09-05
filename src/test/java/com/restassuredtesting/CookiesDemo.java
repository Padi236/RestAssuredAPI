package com.restassuredtesting;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import io.restassured.response.Response;

public class CookiesDemo {
	
	Response res;
	
	@Test
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			//.cookie("AEC","Ad49MVGoXZMXArhHEPbNf6Ef8tbL0TyTEfGTwDd_3EqP0DLI1hegSvBxfIg")
			.log().all();
				
	}
	
	@Test
	void getCookiesInfo()
	{
		Response res = given()
				
					   .when()
					   		.get("https://www.google.com/");
		
		//String cookie_value = res.getCookie("AEC");
		//System.out.println("Value of AEC cookie is==>"+cookie_value);	
			
	
		Map<String,String> cookies_keys	= res.getCookies();
		//System.out.println(cookies_keys.keySet());
		
		for(String eachKey : cookies_keys.keySet()) 
		{
			String cookie_value = res.getCookie(eachKey);
			System.out.println(eachKey+"  "+cookie_value);
		}
		
	}
	
	
	
}
