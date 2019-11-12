package StepDefnitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.Assert;

import Setup.TestSetup;

public class Update_Customer_Test extends TestSetup {

	@When("I update email {string} description {string} and balance {int}")
	public void i_update_email_description_and_balance(String email, String desc, int bal) {
		requestSpecs = requestSpecs.formParam("email", email).formParam("description", desc).formParam("balance", bal);
		log.info("Updated with "+" Email: "+email+" Description: "+desc+" balance: "+bal);

	}

	@And("I send post request for Update_Customer_API")
	public void i_send_post_request_for_Update_Customer_API() {
		response = requestSpecs.post(configProperty.getupdateCustomerAPIEndPoint());
		log.info("Customer ID: "+response.jsonPath().getString("id"));
		log.info("Send update request successfully...");
	    //response.prettyPrint();
	}

	@Then("response contains same email {string} description {string} and balance {int}")
	public void response_contains_same_email_description_and_balance(String email, String description, int balance) {
           
		Assert.assertEquals(response.jsonPath().getString("email"),email);
		log.info("Verified email: "+email);
        Assert.assertEquals(response.jsonPath().getString("description"),description);
        log.info("Verifed description: "+description);
        Assert.assertEquals(response.jsonPath().getInt("balance"),balance);
        log.info("Verified balance: "+balance);
	}

}
