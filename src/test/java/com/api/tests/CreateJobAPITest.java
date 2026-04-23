package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constant.Roles.*;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;
	
	
	@BeforeMethod(description="Creating createjob api request payload")
	public void setup() {
		Customer customer = new Customer("pranjali", "Nirmal", "8400908767", "", "pranjalinirmal08@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("503", "shreeany", "wkt road", "starbucks", "shivaji nagar", "444609", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "12308954197783", "12308954197783", "12308954197783", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		
		 createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONTDESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		
		
	}
	
	
	@Test(description="Verifying if the create job api is able to create Inwarranty job", groups = {"api","regression","smoke"})
	public void createJobAPITest() {
		
		given()
		.spec(requestSpecWithAuth(FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.log().all()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	
		
	}

}
