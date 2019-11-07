package Util;

import org.json.JSONObject;

import Setup.TestSetup;
import io.restassured.response.Response;

public class TestUtils extends TestSetup {

	// To check ID is present method
	public static boolean checkJsonHasKey(String key, Response response) {
		JSONObject json = new JSONObject(response.asString()); // store response in json
		return json.has(key); // check this key is present in response
	}

}
