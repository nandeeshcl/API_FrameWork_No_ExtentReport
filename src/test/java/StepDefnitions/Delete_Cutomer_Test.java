package StepDefnitions;

import org.testng.Assert;

import Setup.TestSetup;
import Util.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Delete_Cutomer_Test extends TestSetup {

	public static String cusID;

	@Given("I have created customer ID")
	public void i_have_created_customer_ID() {
		cusID = TestUtils.getCustomerID();
		//log.info(cusID);
	}

	@When("I send post request for Delete_Customer_API")
	public void i_send_post_request_for_Delete_Customer_API() throws InterruptedException {
		Thread.sleep(2000);
		response = requestSpecs.delete(configProperty.getdeleteCustomerAPIEndPoint() + cusID);
		log.info("Delete request sent successfully...");
		// response.prettyPrint();
	}

	@Then("Id in response is same as ID I sent while deleting request")
	public void id_in_response_is_same_as_ID_I_sent_while_deleting_request() {

		Assert.assertEquals(cusID, response.jsonPath().getString("id"));

		log.info("Customer ID: "+cusID+" field verified ");
	}

	@And("the key deleted is {string}")
	public void the_key_deleted_is(String string) {

		Assert.assertEquals(string, response.jsonPath().getString("deleted"));

		log.info("Deleted field contains the value: "+string+" Verified");
	}

}
