package com.restassuredtesting;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//POJO---Serialization----->JSON Object-----Deserialization--->POJO


public class SerializeDesrialization {
	
	@Test
	void convertPOJO2Json() throws JsonProcessingException
	{	
		//Create Java Object using POJO class
		StudentPOJO pojoData = new StudentPOJO();
		pojoData.setName("Suchi");
		pojoData.setLocation("Wardha");
		pojoData.setContact("8275295754");
		pojoData.setCourses(new String[]{"Jmeter","API"});
		
		//convert java object  ----> json object(serialization)
		//Special Class from jackson
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData;
		
		
			jsonData = 	objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoData);
			System.out.println(jsonData);
		
				
	}
	
	@Test
	void convertJson2POJO() throws JsonMappingException, JsonProcessingException 
	{
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Suchi\",\r\n"
				+ "  \"location\" : \"Wardha\",\r\n"
				+ "  \"contact\" : \"8275295754\",\r\n"
				+ "  \"courses\" : [ \"Jmeter\", \"API\" ]\r\n"
				+ "}";
		
		ObjectMapper objMapper = new ObjectMapper();
		StudentPOJO stud =	objMapper.readValue(jsonData, StudentPOJO.class);
		
		System.out.println(stud.getName());
		System.out.println(stud.getContact());
		System.out.println(stud.getLocation());
		System.out.println(stud.getCourses()[0]);
		System.out.println(stud.getCourses()[1]);
	}
	
	
}
