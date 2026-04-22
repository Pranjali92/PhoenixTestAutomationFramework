package com.api.tests;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	
	
	
	@Test(description="Verify if the userdetails API response is shown correctly", groups = {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		
	
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	}

}
