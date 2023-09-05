package apichaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
		
	@Test	
	void testDeleteUSer(ITestContext context)
	{	
		String bearerToken = "839895546e30296a5cffa7516d7006344304995ad051284d565b00c70f8cfaac";
		int userId = (Integer) context.getSuite().getAttribute("userId");;
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("userId",userId)
						
						
	.when()
		.delete("https://gorest.co.in/public/v2/users/{userId}")
	
	.then()
		.statusCode(204)
		.log().all();
	}
	
}
