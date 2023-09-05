package com.restassuredtesting;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import io.restassured.response.Response;

public class APIWeatherTemp {
	
	public static void main(String[] args)

	{
		
		getWeatherData("2019-03-29 02:00:00");
	}
		
	public static void getWeatherData(String date) {
		Response res = given().queryParam("q", "London,us").queryParam("appid", "b6907d289e10d714a6e88b30761fae22")
				.when().get("https://samples.openweathermap.org/data/2.5/forecast/hourly");

		JSONObject jsonObject = new JSONObject(res.asPrettyString());		
		
		// Pass String as in format: 2019-03-29 02:00:00
		for (int i = 0; i < jsonObject.getJSONArray("list").length(); i++) {
			if (res.jsonPath().get("list[" + i + "].dt_txt").equals(date)) {
				System.out.println(res.jsonPath().get("list[" + i + "].main.temp"));
			}

		}

	}
	
	
}
