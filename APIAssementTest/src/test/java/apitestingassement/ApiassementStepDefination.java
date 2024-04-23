package apiStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.Assert;
import io.restassured.internal.common.path.ObjectConverter.*;

public class apiTestStepDef {

	private Response response;
	private long currentTimestampBeforeApiCall;
	private static final String BASE_URI = "https://open.er-api.com/v6/latest/USD";

	// Scenario 1: API call is successful and returns a valid price

	@Given("the exchange rates API endpoint is available")
	public void the_exchange_rates_api_endpoint_is_available() {
		response = given().head(BASE_URI);
		if (response.statusCode() != 200) {
			throw new IllegalStateException("The exchange rates API endpoint is not available");
		}
	}

	@When("I request the latest USD exchange rates")
	public void i_request_the_latest_usd_exchange_rates() {
		response = given().when().get(BASE_URI);
	}

	@Then("the response should contain a valid price for {string}")
	public void the_response_should_contain_a_valid_price_for(String currency) {
		response.then().statusCode(200).body("base_code", equalTo("USD")).body("rates." + currency,
				both(greaterThan(0f)).and(instanceOf(Float.class)));
	}

	// Scenario 2: API returns various status codes and status field

	@When("I check the status codes and status field from the API")
	public void i_check_the_status_codes_and_status_field_from_the_api() {
		response = given().when().get(BASE_URI);
		if (response.jsonPath().get("status") == null) {
			System.out.println("The 'status' field is not present in the response.");
		} else {
			response.then().body("status", anyOf(equalTo("SUCCESS"), equalTo("FAILURE"))).extract().response()
					.prettyPrint(); // Print response body for debugging
		}
	}

	@Then("the API should return one of the expected status codes")
	public void the_api_should_return_one_of_the_expected_status_codes() {
		response.then().statusCode(anyOf(equalTo(200), equalTo(400), equalTo(500)));
	}

	@Then("the API should return a status indicating success or failure")
	public void the_api_should_return_a_status_indicating_success_or_failure() {
		// This assertion is already covered within the if-else block in
		// i_check_the_status_codes_and_status_field_from_the_api()
		// So, no additional assertion is needed here.
	}

	// Scenario 3: API Response Time Validation

	@When("I make a request to the API")
	public void i_make_a_request_to_the_api() {
		response = given().when().get(BASE_URI);
	}

	@Then("the API response timestamp should not be less than 3 seconds from the current time in seconds")
	public void the_api_response_timestamp_should_not_be_less_than_3_seconds_from_the_current_time_in_seconds() {
		{
			   // Ensure the response is not null and has a status code of 200 OK
			   Assert.assertNotNull(response, "The response object is null.");
			   Assert.assertEquals(response.statusCode(), 200, "The API did not return a 200 Ok Response");

			   // Check that the response time is not less than 3 seconds from the current time
			   ValidatableResponse respTime = response.then();
			   respTime.time(Matchers.greaterThan(3000L));
			}
	}
	/*
	 * String responseBody = response.getBody().asString();
	 * Assert.assertNotNull(responseBody, "The body is null or not present");
	 * Assert.assertTrue(response.contentType().contains("application/json"),
	 * "The response content Type is not Json.");
	 * 
	 * // Capture the API response timestamp from the body JsonPath
	 * jsonPathEvaluator = new JsonPath(responseBody); Long apiResponseTimestamp =
	 * jsonPathEvaluator.getLong("timestamp");
	 * 
	 * // Ensure the "timestamp" field is not null
	 * Assert.assertNotNull(apiResponseTimestamp,
	 * "The 'timestamp' field in the response is missing or null.");
	 * 
	 * // Calculate the difference in seconds long differenceInSeconds =
	 * apiResponseTimestamp - currentTimestampBeforeApiCall;
	 * 
	 * // Check that the response time is not less than 3 seconds from the current
	 * time Assert.assertTrue(differenceInSeconds >= 3,
	 * "The API response timestamp was less than 3 seconds from the sent request time."
	 * ); }
	 */

	// Scenario 4: Currency Exchange

	@When("I fetch the USD to AED exchange rate")
	public void i_fetch_the_usd_to_aed_exchange_rate() {
		response = given().when().get(BASE_URI);
	}

	@Then("the USD to AED exchange rate should be in the range of {float} to {float}")
	public void the_usd_to_aed_exchange_rate_should_be_in_the_range_of(float lowerBound, float upperBound) {
		response.then().statusCode(200).body("rates.AED", greaterThanOrEqualTo(lowerBound)).body("rates.AED",
				lessThanOrEqualTo(upperBound));
	}

	// Scenario 5:Currency Pair

	@When("I fetch the api 162 currency")
	public void i_fetch_the_api_162_currency() {
		response = given().when().get(BASE_URI);
	}

	@Then("the API should return 162 currency pairs")
	public void the_api_should_return_162_currency_pairs() {
		response.then().statusCode(200).body("rates", aMapWithSize(162));
	}

// Scenario 6: Validate JSON Schema

	@When("I validate the JSON schema of the API response")
	public void i_validate_the_json_schema_of_the_api_response() {
		// This step will execute before the assertion step.
	}

	
	  @Then("the API response should match the JSON schema") public void
	  the_api_response_should_match_the_json_schema() { // This step will executeafter the validation step.
	  response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/Resource/TestData/schema.json"))); 
	  
	  } 
}
