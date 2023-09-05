package com.restassuredtesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class LogMethodDemo {
	
	
	@Test
	void testLogs()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			//.get("https://www.google.com/")
		
		.then()
			.log().cookies();
			//.log().body();
			//.log().all();
	}

}
