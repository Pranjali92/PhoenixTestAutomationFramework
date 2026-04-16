package com.api.tests;

import org.testng.annotations.Test;

import static com.api.constant.Roles.*;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("pranjali", "Nirmal", "8400908767", "", "pranjalinirmal08@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("503", "shreeany", "wkt road", "starbucks", "shivaji nagar", "444609", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-02-01T18:30:00.000Z", "14408054197783", "14408054197783", "14408054197783", "2025-02-01T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_OK());
	
		
		
	}

}
