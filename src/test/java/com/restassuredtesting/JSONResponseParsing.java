package com.restassuredtesting;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JSONResponseParsing {

	@Test
	void testJsonResponse() 
	{
		//Approach1
		/*given()
			.contentType("application/json")
			
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[1].title",equalTo("Song of Ice and Fire"));*/
		
		
		//Approach2
		Response res = 			
			given()
				.contentType(ContentType.JSON)		//.contentType("application/json")	
		
			.when()
				.get("http://localhost:3000/store");
		
		System.out.println(res.jsonPath().get("book[1].price"));
		
		//Hard core validations, since order of books can change
		assertEquals(res.getStatusCode(), 200);	
		assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");	
		assertEquals(res.jsonPath().get("book[1].title").toString(), "Song of Ice and Fire");
		assertEquals(res.jsonPath().get("book[1].price"), 1250);
		assertEquals(res.jsonPath().get("book[1].author").toString(), "George R.R. Martin");
		
		//Validations irrespective of order of JSON object
		JSONObject jsonObject = new JSONObject(res.asString());	//converting Response to JSON object type
		for(int i=0; i<jsonObject.getJSONArray("book").length();i++)
		{
			String bookTitle = jsonObject.getJSONArray("book").getJSONObject(i).get("title").toString();
			String bookAuthor = jsonObject.getJSONArray("book").getJSONObject(i).get("author").toString();
			System.out.println(bookTitle+" : "+bookAuthor);
		}
		
		boolean flag = false;
		//To search for book title in the JSON Response
		for(int i=0; i<jsonObject.getJSONArray("book").length();i++)
		{
			String bookTitle = jsonObject.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("Wings of Fire"))
			{
				flag = true; 
				break;
			}
		}
		assertEquals(flag, true,"Book is not present in the response body");
		int sum = 0;
		//Validate Sum of all the books
		for(int i=0; i<jsonObject.getJSONArray("book").length();i++)
		{
			String bookPrice = jsonObject.getJSONArray("book").getJSONObject(i).get("price").toString();
			sum = sum + Integer.parseInt(bookPrice);			
		}
		System.out.println("Sum of all the books is: "+sum);
	}

}
