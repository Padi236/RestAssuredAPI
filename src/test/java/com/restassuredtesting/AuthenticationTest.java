package com.restassuredtesting;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class AuthenticationTest {
	
	@Test
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//internal algorithm is different between Basic, Digest, PreEmptive
	//From User's perspective, all of them needs just username and password
	//Implementation is different in Postman and RestAssured,as
	//they have their own authentications
	
	@Test
	void testDigestAuthentication()
	{
		given()
		.auth().digest("postman", "password")
	
	.when()
		.get("https://postman-echo.com/basic-auth")
	
	.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
	}
	
	//preemptive is combination of basic and digest
	@Test
	void testPreEmptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
	
		.when()
			.get("https://postman-echo.com/basic-auth")
	
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	
	//Bearer authentication is added as part of header
	@Test 
	void testBearerTokenAuthentication()
	{
		String bearerToken = "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";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//oAuth2 is advancement of oAuth1
	//oAuth2 has same algorithm as oAuth1 but less parameters;
	//just need token(condensed to 2 step from 3 steps in oAuth2)
	//Oauth1 needs, consumerKey, consumerSecretKey, accessToken, tokenSecret
	void testOauthAuthentication()
	{
		given()
			.auth().oauth2(DEFAULT_BODY_ROOT_PATH)
			//access token is generated manually from postman
			
			
		.when()
			.get("https://api.github.com/user/repos")
	
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	void testAPIKeyAuth()
	{
		// Here the api key we pass as part of query parameter
		// unlike bearer_token where we pass in request Header
	}
	
}
