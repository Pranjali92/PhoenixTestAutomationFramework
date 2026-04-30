package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

public class LoginAPIDataDrivenTest {	
	
	@Test(description="Verifying if the login api is working for FD user", 
			groups = {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIDataProvider"
			)
	
	
	public void loginAPITest(UserBean userbean)  {
		
		
	given()
		.spec(requestSpec(userbean))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
	}
	

}
