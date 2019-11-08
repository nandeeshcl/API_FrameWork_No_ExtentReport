package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(

		plugin = { "json:target/cucumber.json", "pretty", "html:target/cucumber.html" },

		features = "./src/test/Feature_Files", glue ="StepDefnitions", monochrome = true
     // tags={" @Retrieve_Customer_with_EID"}

)
public class RunCuke extends AbstractTestNGCucumberTests {

}
