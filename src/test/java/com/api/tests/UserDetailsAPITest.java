package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Roles.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {
	
	
	
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		
		Header authHeader = new Header("Authorization" , getToken(SUP));
		given()
		.baseUri(getProperty("BASE_URL"))
		.and()
		.header(authHeader)
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method() 
		.log().body()
		.log().headers()
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(2000L))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	}

}
