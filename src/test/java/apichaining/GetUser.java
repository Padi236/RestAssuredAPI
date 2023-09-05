package apichaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class GetUser {
	
	@Test
	void testGetUser(ITestContext context)
	{	
		int userId = (Integer) context.getSuite().getAttribute("userId");
		
		String bearerToken = "839895546e30296a5cffa7516d7006344304995ad051284d565b00c70f8cfaac";
		
			given()
				.headers("Authorization","Bearer "+bearerToken)
				.pathParam("userId", userId)
		
		
			.when()
				.get("https://gorest.co.in/public/v2/users/{userId}")
		
			.then()
				.statusCode(200)
				.log().all();
	}
	
}
