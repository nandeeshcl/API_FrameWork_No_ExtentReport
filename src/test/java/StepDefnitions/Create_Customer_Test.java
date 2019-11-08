package StepDefnitions;


import Setup.TestSetup;
import Util.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class Create_Customer_Test extends TestSetup {

	@Given("I have valid auth key")
	public void i_have_valid_auth_key() {

		//log = Logger.getLogger(Create_Customer_Test.class);

		// Authentication using basic auth
		// requestSpecs=given().auth().basic(configProperty.getValidAuthKey(),"");

		// Authentication using header
		requestSpecs = given().header("Authorization", "Bearer " + configProperty.getValidAuthKey());

		log.info("Valid Auth Key "+ configProperty.getValidAuthKey()+ " Entered...");

	}

	@Given("I have email {string} and description {string}")
	public void i_have_email_and_description(String pEmail, String pDescription) {
		requestSpecs = requestSpecs.formParam("email", pEmail).formParam("description", pDescription);
  
		log.info("Email "+pEmail+ "and Description "+pDescription+ " entered...");
	}

	@When("I send post request")
	public void i_send_post_request() {
		response = requestSpecs.post(configProperty.getcreateCustomerAPIEndPoint());
 
		log.info("Post request sent...");
		// To print response on console
		// response.prettyPrint();
	}

	@Then("I verify status code is {int}")
	public void i_verify_status_code_is(int statusCode) {
		
		Assert.assertEquals(response.statusCode(), statusCode);
		log.info("Status code "+statusCode+ " verified");

	}

	@Then("email in response should be {string} description should be {string}")
	public void email_in_response_should_be_description_should_be(String pemail, String pdescription) {
		responseJson = response.jsonPath();
		Assert.assertEquals(responseJson.get("email"), pemail);
		Assert.assertEquals(responseJson.get("description"), pdescription);
		log.info("Email "+pemail+ "and Description "+pdescription+ " verified...");

	}

	@Then("I verify {string} is present")
	public void i_verify_is_present(String id) {

		// Check id field is not null
		Assert.assertNotNull(TestUtils.checkJsonHasKey(id, response));
		log.info("ID field "+id+ " checked in response...");

	}
	
	@Given("I have in-valid auth key")
	public void i_have_in_valid_auth_key() {
		// Authentication using header
				requestSpecs = given().header("Authorization", "Bearer " + configProperty.getInvalidAuthKey());

				log.info("In-Valid Auth Key "+ configProperty.getInvalidAuthKey()+ " Entered...");

	}

	@Then("I verify {string} is not present in response")
	public void i_verify_is_not_present_in_response(String id) {
	   Assert.assertFalse(TestUtils.checkJsonHasKey(id, response));
	   log.info("Verify ID field not in response...");
	}

	@Then("response contains the {string}")
	public void response_contains_the(String errorMsg) {
	   responseJson=response.jsonPath();
	   boolean status=responseJson.getString("error.message").contains(errorMsg);
	   Assert.assertTrue(status);
	   log.info("Verified response contains error message "+errorMsg);
	
	
	
	}
}
