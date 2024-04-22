package apitestingassesmentTC;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

public class CurrencyExchangeValidation {
	  @Test
	    public void checkUSDToAEDExchangeRate() {
	        // API Endpoint for retrieving latest USD exchange rates
	        String baseURL = "https://open.er-api.com/v6/latest/USD";
	        
	        given()
	            .when()
	                .get(baseURL)
	            .then()
	                .statusCode(200)                                     // Verify that the response status is 200 (OK)
	                .body("rates.AED", greaterThanOrEqualTo(3.6f))       // Verify that AED rate is greater than or equal to 3.6
	                .body("rates.AED", lessThanOrEqualTo(3.7f));         // Verify that AED rate is less than or equal to 3.7
	    }
	}
