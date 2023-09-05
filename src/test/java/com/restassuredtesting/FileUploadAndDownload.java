package com.restassuredtesting;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test(priority = 1)
	void singleFileUpload()
	{
		File myFile = new File("C:\\Users\\Gangad\\Desktop\\Git.txt");
		
		given()
			.multiPart("file", myFile)
			.contentType("multipart/form-data")
			.contentType(ContentType.MULTIPART)
		
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()
			.statusCode(200)
			.body("fileName",equalTo("Git.txt"));
			
	}
	
	//@Test
	void multiFileUpload()
	{
		File myFile1 = new File("C:\\Users\\Gangad\\Desktop\\Git.txt");
		File myFile2 = new File("C:\\Users\\Gangad\\Desktop\\ExceptionHandling.docx");
			
		//Won't work for each type of API. Depends on how Dev has designed the API
		//File[] fileArray = {myFile1, myFile2};
		//.multiPart("files", fileArray)
		
		given()
			.multiPart("files", myFile1)
			.multiPart("files", myFile2)
			.contentType("multipart/form-data")
			.contentType(ContentType.MULTIPART)
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles   ")
		
		.then()
			.statusCode(200)
			.body("[0].fileName",equalTo("Git.txt"))
			.body("[1].fileName",equalTo("ExceptionHandling.docx"))
			.log().all();
			
	}
	
	@Test(priority = 2)
	void fileDownload()
	{
		
		given()
					
		.when()
			.get("http://localhost:8080/downloadFile/Git.txt")
		
		.then()
			.statusCode(200)
			.log().all();
			
			
		
		
	}
	
}
