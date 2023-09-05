package com.restassuredtesting;
import static io.restassured.RestAssured.*;

public class TestAPIChaining {
	
	String userId;
	
	void testApiChaining()
	{
		String token = "839895546e30296a5cffa7516d7006344304995ad051284d565b00c70f8cfaac";
		
		given()
			.headers("Authorization","Bearer "+token)
		
		.when()
			.get()
		
		.then();
	}
	
}
