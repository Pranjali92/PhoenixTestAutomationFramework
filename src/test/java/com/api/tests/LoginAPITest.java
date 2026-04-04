package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test(description="abc")
	public void loginAPITest() throws IOException {
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
	given()
		 .baseUri(getProperty("BASE_URL"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(userCredentials)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.post("login")
		.then()
		.log().all()
		.statusCode(200)
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		.time(lessThan(2000L));
		
	}
	

}
