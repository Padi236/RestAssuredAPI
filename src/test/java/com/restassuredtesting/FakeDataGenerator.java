package com.restassuredtesting;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
	
	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		System.out.println(firstName);
		String lastName = faker.name().lastName();
		System.out.println(lastName);
		String fullName = faker.name().fullName();
		System.out.println(fullName);
		String userName = faker.name().username();
		System.out.println(userName);
		String passWord = faker.internet().password(6,8);
		System.out.println(passWord);
		String phoneNum = faker.phoneNumber().cellPhone();
		System.out.println(phoneNum);
		String email = faker.internet().safeEmailAddress();
		System.out.println(email);
	}
	
}
