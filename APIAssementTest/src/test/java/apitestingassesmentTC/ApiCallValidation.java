package apitestingassesmentTC;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

public class ApiCallValidation {
	@Test
	public void TestAPICall() {
	        // Base URI for the currency exchange rates API
	        String baseURL = "https://open.er-api.com/v6/latest/USD";

	        // Test case: Verify that the API call is successful and returns valid USD exchange rates
	        given()
	            .when()
	                .get(baseURL)
	            .then()
	                .statusCode(200)                                          // Expecting a successful status code of 200
	                .contentType(equalTo("application/json"))                  // Expecting Content-Type to be 'application/json'
	                .body("base_code", equalTo("USD"))                              // Expecting the base currency to be 'USD'
	                .body("rates", notNullValue())                             // Expecting 'rates' object not to be null
	                .body("rates", hasKey("EUR"))                              // As an example, checking if 'rates' has a key for 'EUR'
	                .body("rates.EUR", greaterThan(0f));                       // Expecting the EUR exchange rate to be greater than 0
	               
	    }
	}