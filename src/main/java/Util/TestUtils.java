package Util;

import org.json.JSONObject;

import Setup.TestSetup;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TestUtils extends TestSetup {

	// To check ID is present method
	public static boolean checkJsonHasKey(String key, Response response) {
		JSONObject json = new JSONObject(response.asString()); // store response in json
		return json.has(key); // check this key is present in response
	}
	
	
	public static String getCustomerID()
	{
		response=given().auth().basic(configProperty.getValidAuthKey(),"").post(configProperty.getcreateCustomerAPIEndPoint());
	    
		String customerID=response.jsonPath().getString("id");
		
		log.info("New Customer ID: "+customerID);
		
		return customerID;
		
		
	
	}

}
