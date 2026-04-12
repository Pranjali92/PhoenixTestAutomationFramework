package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*  ;

public class LoginAPITest {
	
	@Test(description="abc")
	public void loginAPITest() throws IOException {
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
	given()
		.spec(SpecUtil.requestSpec(userCredentials))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
	}
	

}
