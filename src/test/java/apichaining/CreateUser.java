package apichaining;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void testCreateUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "ankita"+faker.name().fullName());
		jsonData.put("email", faker.internet().safeEmailAddress());
		jsonData.put("gender", "Female");
		jsonData.put("status", "inactive");
						
		String bearerToken = "839895546e30296a5cffa7516d7006344304995ad051284d565b00c70f8cfaac";
		 	
		int userId =	given()
							.headers("Authorization","Bearer "+bearerToken)
							.contentType("application/json")	//Passed with contentType: "application/json"; Passed with header: ContentType.JSON
							.body(jsonData.toString())
							//.pathparameters not working
													
						.when()
							.post("https://gorest.co.in/public/v2/users")
							.jsonPath().getInt("id");
													
		System.out.println(userId);
		//context.setAttribute("userId", userId);
		//By this method, this attribute is available across Test level only.
		//That is all other testcase files dependending on this attribute needs to belong to same "Test" in testng.xml
		
		context.getSuite().setAttribute("userId", userId);
		//By this way this attribute is set at suite level
		
	}
	
}
