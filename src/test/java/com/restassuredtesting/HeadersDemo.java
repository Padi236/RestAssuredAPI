package com.restassuredtesting;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test
	void testHeaders() {
			given()

			.when()
				.get("https://www.google.com/")

			.then()
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.and()
				.header("Content-Encoding", "gzip")
				.and()
				.header("Server", "gws").log().all()
				.log().headers();
				

	}
	
	@Test
	void getHeaders() 
	{
		
		Response res = 	given()

						.when()
							.get("https://www.google.com/");
		
		String headerContentType = res.getHeader("Content-Type");
		//System.out.println("Value of Content-Type Header is: "+headerContentType);	
		
		//To get All headers info
		Headers myHeaders = res.getHeaders();
		
		for(Header eachHeader : myHeaders) 
		{
			System.out.println(eachHeader.getName()+" : "+eachHeader.getValue());
		}
		
	}

}
