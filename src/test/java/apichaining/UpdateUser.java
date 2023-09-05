package apichaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void testUpdateUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "Padi"+faker.name().fullName());
		jsonData.put("email", faker.internet().safeEmailAddress());
		jsonData.put("gender", "Male");
		jsonData.put("status", "active");
						
		String bearerToken = "839895546e30296a5cffa7516d7006344304995ad051284d565b00c70f8cfaac";
		int userId = (Integer) context.getSuite().getAttribute("userId");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")	//Passed with contentType: "application/json"; Passed with header: ContentType.JSON
			.body(jsonData.toString())
			.pathParam("userId",userId)
							
							
		.when()
			.put("https://gorest.co.in/public/v2/users/{userId}")
		
		.then()
			.statusCode(200)
			.log().all();
													
		System.out.println(userId);
	}
	
}
