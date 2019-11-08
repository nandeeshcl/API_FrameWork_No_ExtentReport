package StepDefnitions;

import org.testng.Assert;

import Setup.TestSetup;
import Util.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

public class Retrieve_Customer_Test extends TestSetup {

	public String cID;

	@When("I send get request with customerID {string}")
	public void i_send_get_request_with_customerID(String cusID) {
		response = requestSpecs.get(configProperty.getretrieveCustomerAPIEndPoint() + cusID);
		log.info("Get request with customer ID" + cusID + " sent");
	}

	/*
	 * @Then("I verify status code is name{int}") public void
	 * i_verify_status_code_is_name(int statusCode) {
	 * Assert.assertEquals(response.statusCode(), statusCode);
	 * log.info("Verified status code is "+statusCode); }
	 */

	@And("I verify same customer ID present in the response {string}")
	public void i_verify_same_customer_ID_present_in_the_response(String cusID) {
		// System.out.println(response.jsonPath().getString("id"));
		Assert.assertEquals(response.jsonPath().getString("id"), cusID);
		log.info("Customer ID " + cusID + " verifed");
	}

	@When("I get customerID from Createcustomer API")
	public void i_get_customerID_from_Createcustomer_API() {

		cID = TestUtils.getCustomerID();
		log.info("Customer ID obtained is: " + cID);

	}

	@When("I send get request with obtained customerID")
	public void i_send_get_request_with_obtained_customerID() {

		response = requestSpecs.get(configProperty.getretrieveCustomerAPIEndPoint() + cID);
		log.info("Get request is sent with id: " + cID);
		// response.prettyPrint();
	}

	@Then("I verify same customer ID present in the response")
	public void i_verify_same_customer_ID_present_in_the_response() {
		Assert.assertEquals(response.jsonPath().getString("id"), cID);
		log.info("Customer ID " + cID + " verifeid in resposne");
	}

}
