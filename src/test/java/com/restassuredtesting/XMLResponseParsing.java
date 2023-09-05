package com.restassuredtesting;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XMLResponseParsing {

	@Test
	void testXMLResponse()
	{
		//Approach1
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=2")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page",equalTo("2"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0 ].name",equalTo("ASCAS"));
		
		
		//Approach2
		Response res = 
				given()
				
				.when()
					.get("http://restapi.adequateshop.com/api/Traveler?page=2");

		assertEquals(res.getStatusCode(), 200);
		assertEquals(res.getContentType(), "application/xml; charset=utf-8");	
		assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		assertEquals(pageNo, "2");
		
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0 ].name").toString();
		assertEquals(travelerName, "ASCAS");
		
		//Approach3: Validate total number of Travellers
		XmlPath xmlobj = new XmlPath(res.asString());
		List<String> travellerList = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		assertEquals(travellerList.size(), 10); 
		
		boolean flag = false;
		//Validation3: validate the traveller name
		for (String traveller : travellerList) 
		{
			if(traveller.equals("Albert Petoyan"))
			{
				flag = true;
				break;
			}
		}
		
		assertEquals(flag, true,"Traveller is not present in the response");
	}
	
}
