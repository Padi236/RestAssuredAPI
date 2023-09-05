package com.restassuredtesting;

import java.util.Scanner;

import org.json.JSONObject;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIWeatherForeCast {


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
	

	public static void getWindSpeed(String date) {
		Response res = given().queryParam("q", "London,us").queryParam("appid", "b6907d289e10d714a6e88b30761fae22")
				.when().get("https://samples.openweathermap.org/data/2.5/forecast/hourly");

		JSONObject jsonObject = new JSONObject(res.asPrettyString());

		for (int i = 0; i < jsonObject.getJSONArray("list").length(); i++) {
			if (res.jsonPath().get("list[" + i + "].dt_txt").equals(date)) {
				System.out.println(res.jsonPath().get("list[" + i + "].wind.speed"));
			}

		}
	}
	
	public static void getWindPressure(String date) {
		Response res = given().queryParam("q", "London,us").queryParam("appid", "b6907d289e10d714a6e88b30761fae22")
				.when().get("https://samples.openweathermap.org/data/2.5/forecast/hourly");

		JSONObject jsonObject = new JSONObject(res.asPrettyString());

		for (int i = 0; i < jsonObject.getJSONArray("list").length(); i++) {
			if (res.jsonPath().get("list[" + i + "].dt_txt").equals(date)) {
				System.out.println(res.jsonPath().get("list[" + i + "].main.pressure"));
			}

		}
	}

	public static void main(String[] args) {
		int option;
		String date="";

		do {
			System.out.println();
			System.out.println("1. Get Weather");// 1. Get Weather
			System.out.println("2. Get Windspeed");// 2. Get Windspeed
			System.out.println("3. Get Pressure");// 3. Get Pressure
			System.out.println("0. Exit");// 0. Exit
			System.out.println("Enter Option: ");
			Scanner sc = new Scanner(System.in);
			option = sc.nextInt();		
				
			switch(option)
			
			{
				case 1: 
					System.out.println("Enter date in format(YYYY-MM-DD HH:mm:ss):");
					Scanner sc1 = new Scanner(System.in);
					date= sc1.nextLine();
					System.out.println(date);
					getWeatherData(date);
					break;
				case 2:
					System.out.println("Enter date in format(YYYY-MM-DD HH:mm:ss):");
					Scanner sc2 = new Scanner(System.in);
					date= sc2.nextLine();
					System.out.println(date);
					getWindSpeed(date);
					break;
				case 3:
					System.out.println("Enter date in format(YYYY-MM-DD HH:mm:ss):");
					Scanner sc3 = new Scanner(System.in);
					date= sc3.nextLine();
					System.out.println(date);
					getWindPressure(date);
				case 0:
					break;
				default:
					System.out.println("Please Enter correct option......");
			}

		} while (option != 0);
		
		System.out.println("Thanks for visiting");
	}

}
